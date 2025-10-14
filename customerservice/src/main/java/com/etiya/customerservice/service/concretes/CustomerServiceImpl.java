package com.etiya.customerservice.service.concretes;



import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.customerservice.repository.CorporateCustomerRepository;
import com.etiya.customerservice.repository.IndividualCustomerRepository;
import com.etiya.customerservice.service.abstracts.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final IndividualCustomerRepository individualCustomerRepository;
    private final CorporateCustomerRepository corporateCustomerRepository;


    public CustomerServiceImpl(IndividualCustomerRepository individualCustomerRepository,
                               CorporateCustomerRepository corporateCustomerRepository, CorporateCustomerRepository corporateCustomerRepository1) {
        this.individualCustomerRepository = individualCustomerRepository;

        this.corporateCustomerRepository = corporateCustomerRepository1;
    }

    @Override
    public boolean existsById(int id) {
        return false;
    }

    @Override
    public String getCustomerType(int id) {
        // Individual Customer mı?
        if (individualCustomerRepository.existsById(id)) {
            return "INDIVIDUAL";
        }

        if (corporateCustomerRepository.existsById(id)) {
            return "CORPORATE";
        }

        throw new BusinessException("Customer not found with id: " + id);
    }
}
