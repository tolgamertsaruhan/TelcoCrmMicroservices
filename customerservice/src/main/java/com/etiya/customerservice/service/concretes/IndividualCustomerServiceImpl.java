package com.etiya.customerservice.service.concretes;



import com.etiya.customerservice.domain.entities.IndividualCustomer;
import com.etiya.customerservice.repository.IndividualCustomerRepository;
import com.etiya.customerservice.service.abstracts.IndividualCustomerService;
import com.etiya.customerservice.service.mappings.IndividualCustomerMapper;
import com.etiya.customerservice.service.requests.individualcustomer.CreateIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.individualcustomer.CreatedIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomer.GetIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomer.GetListIndividualCustomerResponse;
import com.etiya.customerservice.service.rules.IndividualCustomerBusinessRules;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IndividualCustomerServiceImpl implements IndividualCustomerService {
    //Repo kullanımı
    private IndividualCustomerRepository individualCustomerRepository;
    private final IndividualCustomerBusinessRules rules;

    public IndividualCustomerServiceImpl(IndividualCustomerRepository individualCustomerRepository, IndividualCustomerBusinessRules rules) {
        this.individualCustomerRepository = individualCustomerRepository; //Dependency injection
        this.rules = rules;
    }

    @Override
    public CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest request) {

        rules.checkIfIndividualCustomerExistsByIdentityNumber(request.getNationalId());

        IndividualCustomer individualCustomer = IndividualCustomerMapper.INSTANCE.individualCustomerFromCreateIndividualCustomerRequest(request);
        IndividualCustomer createdIndividualCustomer = individualCustomerRepository.save(individualCustomer);
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
