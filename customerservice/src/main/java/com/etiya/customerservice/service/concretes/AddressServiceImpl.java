package com.etiya.customerservice.service.concretes;


import com.etiya.common.events.CreateAddressEvent;
import com.etiya.common.events.CreateCustomerEvent;
import com.etiya.common.events.DeletedAddressEvent;
import com.etiya.common.events.UpdatedAddressEvent;
import com.etiya.customerservice.domain.entities.*;
import com.etiya.customerservice.repository.AddressRepository;
import com.etiya.customerservice.repository.DistrictRepository;
import com.etiya.customerservice.service.abstracts.AddressService;
import com.etiya.customerservice.service.abstracts.DistrictService;
import com.etiya.customerservice.service.mappings.AddressMapper;
import com.etiya.customerservice.service.mappings.CityMapper;
import com.etiya.customerservice.service.requests.address.CreateAddressRequest;
import com.etiya.customerservice.service.requests.address.UpdateAddressRequest;
import com.etiya.customerservice.service.responses.address.CreatedAddressResponse;
import com.etiya.customerservice.service.responses.address.GetAddressResponse;
import com.etiya.customerservice.service.responses.address.GetListAddressResponse;
import com.etiya.customerservice.service.responses.address.UpdatedAddressResponse;
import com.etiya.customerservice.service.responses.district.GetDistrictResponse;
import com.etiya.customerservice.service.responses.individualcustomer.GetIndividualCustomerResponse;
import com.etiya.customerservice.service.rules.AddressBusinessRules;
import com.etiya.customerservice.transport.kafka.producer.address.CreateAddressProducer;
import com.etiya.customerservice.transport.kafka.producer.address.DeletedAddressProducer;
import com.etiya.customerservice.transport.kafka.producer.address.UpdatedAddressProducer;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressBusinessRules addressBusinessRules;
    private final DistrictService districtService;

    private final DeletedAddressProducer deletedAddressProducer;

    private final UpdatedAddressProducer updatedAddressProducer;

    private  final CreateAddressProducer createAddressProducer;


    public AddressServiceImpl(AddressRepository addressRepository, AddressBusinessRules addressBusinessRules, DeletedAddressProducer deletedAddressProducer, UpdatedAddressProducer updatedAddressProducer, CreateAddressProducer createAddressProducer, DistrictService districtService) {

        this.addressBusinessRules  = addressBusinessRules;
        this.addressRepository = addressRepository;
        this.deletedAddressProducer = deletedAddressProducer;
        this.updatedAddressProducer = updatedAddressProducer;
        this.createAddressProducer = createAddressProducer;
        this.districtService = districtService;
    }

   // @Override
   // public void add(Address address) {
   //     addressRepository.save(address);
   // }


    @Override
    public CreatedAddressResponse add(CreateAddressRequest request) {
       Address address = AddressMapper.INSTANCE.addressFromCreateAddressRequest(request);

        Address createdAddress = addressRepository.save(address);
        District district = districtService.findById(request.getDistrictId());
        City city = district.getCity();

        CreateAddressEvent event =
                new CreateAddressEvent(createdAddress.getId().toString(),
                        createdAddress.getCustomer().getId().toString(),
                        district.getName(),
                        city.getName(),
                        createdAddress.getStreet(),
                        createdAddress.getHouseNumber(),
                        createdAddress.getDescription(),
                        createdAddress.isDefault());

        createAddressProducer.produceAddressCreated(event);


       CreatedAddressResponse response = AddressMapper.INSTANCE.createdAddressResponseFromAddress(createdAddress);
        return response;
    }

    @Override
    public List<GetListAddressResponse> getList() {
        List<Address> addressList = addressRepository.findAll();
        List<GetListAddressResponse> response = AddressMapper.INSTANCE.getListAddressResponsesFromAddressList(addressList);
        return response;
    }

    @Override
    public void delete(UUID id) { //kalıcı silme- hard delete

        addressBusinessRules.checkIfBillingAccountExists(id);

        Address deletedAddress = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));

        DeletedAddressEvent event =
                new DeletedAddressEvent(deletedAddress.getId().toString(),
                        deletedAddress.getCustomer().getId().toString(),
                        deletedAddress.getDistrict().getName(),
                        deletedAddress.getDistrict().getCity().getName(),
                        deletedAddress.getStreet(),
                        deletedAddress.getHouseNumber(),
                        deletedAddress.getDescription(),
                        deletedAddress.isDefault(),
                        deletedAddress.getDeletedDate().toString());

        deletedAddressProducer.produceAddressDeleted(event);
        addressRepository.deleteById(id);

    }

    @Override
    public void softDelete(String id) {
        UUID uuid = UUID.fromString(id);
        Address address = addressRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Address not found"));
        address.setDeletedDate(LocalDateTime.now());

        DeletedAddressEvent event =
                new DeletedAddressEvent(address.getId().toString(),
                        address.getCustomer().getId().toString(),
                        address.getDistrict().getName(),
                        address.getDistrict().getCity().getName(),
                        address.getStreet(),
                        address.getHouseNumber(),
                        address.getDescription(),
                        address.isDefault(),
                        address.getDeletedDate().toString());

        deletedAddressProducer.produceAddressDeleted(event);
        addressRepository.save(address);
    }

    @Override
    public UpdatedAddressResponse update(UpdateAddressRequest request) {// 1️⃣ Mevcut adresi bul
        Address oldAddress = addressRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Address not found"));

        // 2️⃣ Manuel olarak alanları güncelle
        oldAddress.setStreet(request.getStreet());
        oldAddress.setHouseNumber(request.getHouseNumber());
        oldAddress.setDescription(request.getDescription());
        oldAddress.setDefault(request.getDefault() != null ? request.getDefault() : false);

        // District bilgisi güncellenmiş olabilir
        District district = districtService.findById(request.getDistrictId());
        oldAddress.setDistrict(district);

        // 3️⃣ Güncellenmiş adresi kaydet
        Address updatedAddress = addressRepository.save(oldAddress);

        // City bilgisini district üzerinden al
        City city = district.getCity();

        // 4️⃣ Event gönder (Kafka veya Stream için)
        UpdatedAddressEvent event = new UpdatedAddressEvent(
                updatedAddress.getId().toString(),
                updatedAddress.getCustomer().getId().toString(),
                updatedAddress.getStreet(),
                updatedAddress.getHouseNumber(),
                updatedAddress.getDescription(),
                district.getName(),
                city.getName(),
                updatedAddress.isDefault()
        );

        updatedAddressProducer.produceAddressUpdated(event);

        // 5️⃣ Response objesini manuel oluştur
        UpdatedAddressResponse response = new UpdatedAddressResponse();
        response.setId(updatedAddress.getId());
        response.setStreet(updatedAddress.getStreet());
        response.setHouseNumber(updatedAddress.getHouseNumber());
        response.setDescription(updatedAddress.getDescription());
        response.setDefault(updatedAddress.isDefault());
        response.setDistrictId(district.getId());
        response.setCustomerId(updatedAddress.getCustomer().getId());

        return response;
    }

    @Override
    public GetAddressResponse getById(UUID id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
        GetAddressResponse response = AddressMapper.INSTANCE.getAddressResponseFromAddress(address);
        return response;
    }

    @Override
    public List<GetAddressResponse> getByCustomerId(String customerId) {

        try {
            UUID uuid = UUID.fromString(customerId);
            List<Address> addresses = addressRepository.findByCustomerId(uuid);

            return addresses.stream().map(address -> {
                GetAddressResponse response = new GetAddressResponse();
                response.setId(address.getId());
                response.setStreet(address.getStreet());
                response.setHouseNumber(address.getHouseNumber());
                response.setDescription(address.getDescription());
                response.setDefault(address.isDefault());
                response.setDistrictId(address.getDistrict().getId());
                response.setCustomerId(address.getCustomer().getId());
                response.setDistrictName(address.getDistrict().getName());
                response.setCityName(address.getDistrict().getCity().getName());
                return response;
            }).collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("UUID Fail");
        }
    }


/*
    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }*/


}
