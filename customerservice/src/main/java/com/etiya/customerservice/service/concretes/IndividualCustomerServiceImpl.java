package com.etiya.customerservice.service.concretes;



import com.etiya.common.events.CreateCustomerEvent;
import com.etiya.customerservice.domain.entities.IndividualCustomer;
import com.etiya.customerservice.repository.IndividualCustomerRepository;
import com.etiya.customerservice.service.abstracts.IndividualCustomerService;
import com.etiya.customerservice.service.mappings.IndividualCustomerMapper;
import com.etiya.customerservice.service.requests.individualcustomer.CreateIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.individualcustomer.CreatedIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomer.GetIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomer.GetListIndividualCustomerResponse;
import com.etiya.customerservice.service.rules.IndividualCustomerBusinessRules;
import com.etiya.customerservice.transport.kafka.producer.customer.CreateCustomerProducer;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IndividualCustomerServiceImpl implements IndividualCustomerService {
    //Repo kullanımı
    private IndividualCustomerRepository individualCustomerRepository;
    private final IndividualCustomerBusinessRules rules;
    private final CreateCustomerProducer createCustomerProducer;


    public IndividualCustomerServiceImpl(IndividualCustomerRepository individualCustomerRepository, IndividualCustomerBusinessRules rules, CreateCustomerProducer createCustomerProducer) {
        this.individualCustomerRepository = individualCustomerRepository; //Dependency injection
        this.rules = rules;
        this.createCustomerProducer = createCustomerProducer;

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
                        createdIndividualCustomer.getLastName(),
                        createdIndividualCustomer.getNationalId(),
                        createdIndividualCustomer.getMotherName(),
                        createdIndividualCustomer.getFatherName(),
                        createdIndividualCustomer.getGender());
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
