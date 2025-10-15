package com.etiya.customerservice.service.concretes;


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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressBusinessRules addressBusinessRules;

    public AddressServiceImpl(AddressRepository addressRepository,  AddressBusinessRules addressBusinessRules) {
        this.addressBusinessRules  = addressBusinessRules;
        this.addressRepository = addressRepository;
    }

   // @Override
   // public void add(Address address) {
   //     addressRepository.save(address);
   // }

    @Override
    public CreatedAddressResponse add(CreateAddressRequest request) {
       Address address = AddressMapper.INSTANCE.addressFromCreateAddressRequest(request);
       Address createdAddress = addressRepository.save(address);
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
