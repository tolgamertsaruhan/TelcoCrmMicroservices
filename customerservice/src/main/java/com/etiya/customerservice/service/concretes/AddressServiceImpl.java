package com.etiya.customerservice.service.concretes;


import com.etiya.common.events.CreateAddressEvent;
import com.etiya.common.events.CreateCustomerEvent;
import com.etiya.common.events.DeletedAddressEvent;
import com.etiya.common.events.UpdatedAddressEvent;
import com.etiya.customerservice.domain.entities.Address;
import com.etiya.customerservice.repository.AddressRepository;
import com.etiya.customerservice.service.abstracts.AddressService;
import com.etiya.customerservice.service.mappings.AddressMapper;
import com.etiya.customerservice.service.requests.address.CreateAddressRequest;
import com.etiya.customerservice.service.requests.address.UpdateAddressRequest;
import com.etiya.customerservice.service.responses.address.CreatedAddressResponse;
import com.etiya.customerservice.service.responses.address.GetAddressResponse;
import com.etiya.customerservice.service.responses.address.GetListAddressResponse;
import com.etiya.customerservice.service.responses.address.UpdatedAddressResponse;
import com.etiya.customerservice.service.rules.AddressBusinessRules;
import com.etiya.customerservice.transport.kafka.producer.address.CreateAddressProducer;
import com.etiya.customerservice.transport.kafka.producer.address.DeletedAddressProducer;
import com.etiya.customerservice.transport.kafka.producer.address.UpdatedAddressProducer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressBusinessRules addressBusinessRules;

    private final DeletedAddressProducer deletedAddressProducer;

    private final UpdatedAddressProducer updatedAddressProducer;

    private  final CreateAddressProducer createAddressProducer;
    public AddressServiceImpl(AddressRepository addressRepository, AddressBusinessRules addressBusinessRules, DeletedAddressProducer deletedAddressProducer, UpdatedAddressProducer updatedAddressProducer, CreateAddressProducer createAddressProducer) {
        this.addressBusinessRules  = addressBusinessRules;
        this.addressRepository = addressRepository;
        this.deletedAddressProducer = deletedAddressProducer;
        this.updatedAddressProducer = updatedAddressProducer;
        this.createAddressProducer = createAddressProducer;
    }

   // @Override
   // public void add(Address address) {
   //     addressRepository.save(address);
   // }

    @Override
    public CreatedAddressResponse add(CreateAddressRequest request) {
       Address address = AddressMapper.INSTANCE.addressFromCreateAddressRequest(request);
       Address createdAddress = addressRepository.save(address);

        CreateAddressEvent event =
                new CreateAddressEvent(createdAddress.getId().toString(),
                        createdAddress.getCustomer().getId().toString(),
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
                        deletedAddress.getCustomer().getId().toString());

        deletedAddressProducer.produceAddressDeleted(event);
        addressRepository.deleteById(id);

    }

    @Override
    public void softDelete(UUID id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
        address.setDeletedDate(LocalDateTime.now());
        addressRepository.save(address);
    }

    @Override
    public UpdatedAddressResponse update(UpdateAddressRequest request) {
        Address oldAddress = addressRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Address not found"));

        Address address =  AddressMapper.INSTANCE.addressFromUpdateAddressRequest(request,oldAddress);
        Address updatedAddress = addressRepository.save(address);

        UpdatedAddressEvent event =
                new UpdatedAddressEvent(updatedAddress.getId().toString(),
                        updatedAddress.getCustomer().getId().toString(),
                        updatedAddress.getStreet(),
                        updatedAddress.getHouseNumber(),
                        updatedAddress.getDescription(),
                        updatedAddress.isDefault());

        updatedAddressProducer.produceAddressUpdated(event);

        UpdatedAddressResponse response = AddressMapper.INSTANCE.updatedAddressResponseFromAddress(updatedAddress);
        return response;
    }

    @Override
    public GetAddressResponse getById(UUID id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
        GetAddressResponse response = AddressMapper.INSTANCE.getAddressResponseFromAddress(address);
        return response;
    }


/*
    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }*/


}
