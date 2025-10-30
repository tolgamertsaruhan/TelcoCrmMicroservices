package com.etiya.customerservice.service.concretes;



import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.common.events.CreateCustomerEvent;
import com.etiya.customerservice.domain.entities.*;
import com.etiya.customerservice.domain.enums.ContactMediumType;
import com.etiya.customerservice.repository.AddressRepository;
import com.etiya.customerservice.repository.ContactMediumRepository;
import com.etiya.customerservice.repository.IndividualCustomerRepository;
import com.etiya.customerservice.service.abstracts.DistrictService;
import com.etiya.customerservice.service.abstracts.IndividualCustomerService;
import com.etiya.customerservice.service.mappings.IndividualCustomerMapper;
import com.etiya.customerservice.service.requests.individualcustomer.CreateFullIndividualCustomerRequest;
import com.etiya.customerservice.service.requests.individualcustomer.CreateIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.address.CreatedAddressResponse;
import com.etiya.customerservice.service.responses.contactmedium.CreatedContactMediumResponse;
import com.etiya.customerservice.service.responses.individualcustomer.CreateFullIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomer.CreatedIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomer.GetIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomer.GetListIndividualCustomerResponse;
import com.etiya.customerservice.service.rules.IndividualCustomerBusinessRules;
import com.etiya.customerservice.transport.kafka.producer.customer.CreateCustomerProducer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class IndividualCustomerServiceImpl implements IndividualCustomerService {
    //Repo kullanımı
    private IndividualCustomerRepository individualCustomerRepository;
    private final IndividualCustomerBusinessRules rules;
    private final CreateCustomerProducer createCustomerProducer;

    private final AddressRepository addressRepository;
    private final ContactMediumRepository contactMediumRepository;

    private final DistrictService districtService;


    public IndividualCustomerServiceImpl(IndividualCustomerRepository individualCustomerRepository, IndividualCustomerBusinessRules rules, CreateCustomerProducer createCustomerProducer, AddressRepository addressRepository, ContactMediumRepository contactMediumRepository, DistrictService districtService) {
        this.individualCustomerRepository = individualCustomerRepository; //Dependency injection
        this.rules = rules;
        this.createCustomerProducer = createCustomerProducer;

        this.addressRepository = addressRepository;
        this.contactMediumRepository = contactMediumRepository;
        this.districtService = districtService;
    }

    @Override
    public CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest request) {

        rules.checkIfIndividualCustomerExistsByIdentityNumber(request.getNationalId());

        IndividualCustomer individualCustomer = IndividualCustomerMapper.INSTANCE.individualCustomerFromCreateIndividualCustomerRequest(request);
        IndividualCustomer createdIndividualCustomer = individualCustomerRepository.save(individualCustomer);
        CreateCustomerEvent event =
                new CreateCustomerEvent(createdIndividualCustomer.getId().toString(),
                        createdIndividualCustomer.getCustomerNumber(),
                        createdIndividualCustomer.getFirstName(),
                        createdIndividualCustomer.getMiddleName(),
                        createdIndividualCustomer.getLastName(),
                        createdIndividualCustomer.getNationalId(),
                        createdIndividualCustomer.getMotherName(),
                        createdIndividualCustomer.getFatherName(),
                        createdIndividualCustomer.getGender(),
                        createdIndividualCustomer.getDateOfBirth());
        createCustomerProducer.produceCustomerCreated(event);
        CreatedIndividualCustomerResponse response =
                IndividualCustomerMapper.INSTANCE.createdIndividualCustomerResponseFromIndividualCustomer(createdIndividualCustomer);
        return response;

    }

    @Override
    public List<GetListIndividualCustomerResponse> getList() {
        List<IndividualCustomer> individualCustomers = individualCustomerRepository.findAll();
        List<GetListIndividualCustomerResponse> responses = IndividualCustomerMapper.INSTANCE.getListIndividualCustomerResponseFromIndividualCustomers(individualCustomers);
        return responses;
    }

    @Override
    public GetIndividualCustomerResponse getByLastName(String lastName) {
        IndividualCustomer individualCustomer =
                individualCustomerRepository.findByLastName(lastName).orElseThrow(() -> new RuntimeException("Individual Customer not found"));
        GetIndividualCustomerResponse response =
                IndividualCustomerMapper.INSTANCE.getIndividualCustomerResponseFromIndividualCustomers(individualCustomer);
        return response;
    }

    @Override
    public List<GetListIndividualCustomerResponse> getByFirstNameStartingWith(String prefix) {
        List<IndividualCustomer> individualCustomers =
                individualCustomerRepository.findByFirstNameStartingPrefix(prefix);
        List<GetListIndividualCustomerResponse> responses =
                IndividualCustomerMapper.INSTANCE.getListIndividualCustomerResponseFromIndividualCustomers(individualCustomers);
        return responses;
    }

    @Override
    public List<GetListIndividualCustomerResponse> getByCustomerNumberPattern(String pattern) {
        List<IndividualCustomer> individualCustomers =
                individualCustomerRepository.findByCustomerNumberPattern(pattern);
        List<GetListIndividualCustomerResponse> responses =
                IndividualCustomerMapper.INSTANCE.getListIndividualCustomerResponseFromIndividualCustomers(individualCustomers);
        return responses;
    }

    @Override
    @Transactional
    public CreateFullIndividualCustomerResponse createFullIndividualCustomer(CreateFullIndividualCustomerRequest request) {

        // müşteri oluşturma isteğini aldık
        CreateIndividualCustomerRequest customerRequest = request.getCreateIndividualCustomerRequest();

        IndividualCustomer customer = new IndividualCustomer();
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setMiddleName(customerRequest.getMiddleName());
        customer.setNationalId(customerRequest.getNationalId());
        customer.setFatherName(customerRequest.getFatherName());
        customer.setMotherName(customerRequest.getMotherName());
        customer.setGender(customerRequest.getGender());
        customer.setDateOfBirth(customerRequest.getDateOfBirth());

        if(individualCustomerRepository.existsByNationalId(customer.getNationalId())){
            throw new BusinessException("National ID already exists");
        }else {
            individualCustomerRepository.save(customer);
            CreatedIndividualCustomerResponse customerResponse = new CreatedIndividualCustomerResponse(
                    customer.getId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getMiddleName(),
                    customer.getNationalId(),
                    customer.getMotherName(),
                    customer.getFatherName(),
                    customer.getDateOfBirth(),
                    customer.getGender()
            );

            List<CreatedAddressResponse> addressResponses = request.getAddressRequestList().stream().map(addrReq -> {
                Address address = new Address();
                address.setStreet(addrReq.getStreet());
                address.setHouseNumber(addrReq.getHouseNumber());
                address.setDescription(addrReq.getDescription());
                address.setDefault(addrReq.isDefault());
                District district = districtService.findById(addrReq.getDistrictId());
                //City city = district.getCity();
                address.setDistrict(district);
                address.setCustomer(customer);
                Address saved = addressRepository.save(address);

                return new CreatedAddressResponse(
                        saved.getId(),
                        saved.getStreet(),
                        saved.getHouseNumber(),
                        saved.getDescription(),
                        saved.isDefault(),
                        saved.getDistrict().getId(),
                        saved.getCustomer().getId()
                );
            }).toList();

            List<CreatedContactMediumResponse> contactResponses = request.getCreateContactMediumRequests().stream().map(conReq -> {
                ContactMedium contact = new ContactMedium();
                contact.setType(ContactMediumType.valueOf(conReq.getType().toUpperCase()));
                contact.setValue(conReq.getValue());
                contact.setPrimary(conReq.isPrimary());
                contact.setCustomer(customer);
                ContactMedium saved = contactMediumRepository.save(contact);

                return new CreatedContactMediumResponse(
                        saved.getId(),
                        saved.getCustomer().getId(),
                        saved.getType(),
                        saved.getValue(),
                        saved.isPrimary()
                );
            }).toList();

            CreateFullIndividualCustomerResponse fullResponse = new CreateFullIndividualCustomerResponse();
            fullResponse.setCustomerResponse(customerResponse);
            fullResponse.setAddressResponse(addressResponses);
            fullResponse.setCreatedContactMediumResponses(contactResponses);
            return fullResponse;

        }




    }

    @Override
    public boolean existsByNationalId(String nationalId) {
        return individualCustomerRepository.existsByNationalId(nationalId);
    }


    // controller da get full customer by id için service metodu
    /*@Override
    public CreateFullIndividualCustomerResponse getFullIndividualCustomer(UUID id) {
        IndividualCustomer customer = individualCustomerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<Address> addresses = addressRepository.findByCustomerId(id);
        List<ContactMedium> contacts = contactMediumRepository.findByCustomerId(id);

        CreateFullIndividualCustomerResponse response = new CreateFullIndividualCustomerResponse();
        response.setCustomerResponse(mapper.createdIndividualCustomerResponseFromIndividualCustomer(customer));
        response.setAddressResponse(addresses.stream()
                .map(mapper::createdAddressResponseFromAddress)
                .collect(Collectors.toList()));
        response.setCreatedContactMediumResponses(contacts.stream()
                .map(mapper::createdContactMediumResponseFromContactMedium)
                .collect(Collectors.toList()));

        return response;
    }*/



    /*

    @Override
    public List<GetListIndividualCustomerResponse> findAllWithAddresses() {
        List<IndividualCustomer> individualCustomers = individualCustomerRepository.findAllWithAddresses();
        List<GetListIndividualCustomerResponse> responses = IndividualCustomerMapper.INSTANCE.getListIndividualCustomerResponseFromIndividualCustomers(individualCustomers);
        return responses;
    }*/


    /*@Override
    public void add(IndividualCustomer individualCustomer) {
       // individualCustomer.setCustomerNumber(individualCustomer.generateCustomerNumber());
        individualCustomerRepository.save(individualCustomer);
    }*/


}
