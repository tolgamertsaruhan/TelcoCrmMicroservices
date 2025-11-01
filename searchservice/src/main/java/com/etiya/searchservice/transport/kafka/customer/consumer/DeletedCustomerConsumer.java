package com.etiya.searchservice.transport.kafka.customer.consumer;

import com.etiya.common.events.DeletedAddressEvent;
import com.etiya.common.events.DeletedCustomerEvent;
import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.CustomerSearch;
import com.etiya.searchservice.service.CustomerSearchService;
import com.etiya.searchservice.transport.kafka.address.consumer.DeletedAddressConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class DeletedCustomerConsumer {

    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(DeletedCustomerConsumer.class);



    public DeletedCustomerConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<DeletedCustomerEvent> customerDeleted() {
        return event -> {
            CustomerSearch customerSearch = new CustomerSearch(event.customerId(), event.customerNumber(), event.firstName(), event.middleName(),
                    event.lastName(), event.nationalId(), event.motherName(), event.fatherName(),
                    event.gender(),event.dateOfBirth(),event.deletedDate());
            customerSearchService.softDeleteCustomer(customerSearch);
            LOGGER.info(String.format("Deleted customer => %s", event.customerId()));
        };
    }


}
