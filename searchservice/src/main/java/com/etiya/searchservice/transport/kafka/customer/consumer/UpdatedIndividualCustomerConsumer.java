package com.etiya.searchservice.transport.kafka.customer.consumer;

import com.etiya.common.events.UpdatedAddressEvent;
import com.etiya.common.events.UpdatedIndividualCustomerEvent;
import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.CustomerSearch;
import com.etiya.searchservice.service.CustomerSearchService;
import com.etiya.searchservice.transport.kafka.address.consumer.UpdatedAddressConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class UpdatedIndividualCustomerConsumer {
    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(UpdatedIndividualCustomerConsumer.class);

    public UpdatedIndividualCustomerConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;

    }

    @Bean
    public Consumer<UpdatedIndividualCustomerEvent> customerUpdated() {
        return event -> {
            CustomerSearch customerSearch = new CustomerSearch(event.customerId(), event.firstName(), event.middleName(), event.lastName(), event.nationalId(), event.motherName(), event.fatherName(), event.gender(), event.dateOfBirth());
            customerSearchService.updateCustomer(customerSearch);
            LOGGER.info(String.format("Updated customer => %s", event.customerId()));
        };
    }
}
