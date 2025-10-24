package com.etiya.searchservice.transport.kafka.address.consumer;

import com.etiya.common.events.CreateAddressEvent;
import com.etiya.common.events.CreateCustomerEvent;
import com.etiya.common.events.UpdatedAddressEvent;
import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.CustomerSearch;
import com.etiya.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Configuration
public class UpdatedAddressConsumer {

    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(UpdatedAddressConsumer.class);

    public UpdatedAddressConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;

    }

    @Bean
    public Consumer<UpdatedAddressEvent> addressUpdated() {
        return event -> {
            Address addressSearch = new Address(event.addressId(),  event.street(), event.houseNumber(), event.description(), event.isDefault());
            customerSearchService.updateAddress(addressSearch);
            LOGGER.info(String.format("Updated address => %s", event.addressId()));
        };
    }

//    @KafkaListener(topics = "update-address", groupId = "update-address-group")
//    public void consume(UpdatedAddressEvent event){
//        LOGGER.info(String.format("Updated address => %s", event.addressId()));
//        Address addressSearch = new Address(event.addressId(),  event.street(), event.houseNumber(), event.description(), event.isDefault());
//        customerSearchService.updateAddress(event.customerId(), addressSearch);
//    }
}
