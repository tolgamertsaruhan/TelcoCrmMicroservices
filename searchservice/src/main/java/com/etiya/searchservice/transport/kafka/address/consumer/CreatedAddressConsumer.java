package com.etiya.searchservice.transport.kafka.address.consumer;

import com.etiya.common.events.CreateAddressEvent;
import com.etiya.common.events.DeletedAddressEvent;
import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class CreatedAddressConsumer {

    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(CreatedAddressConsumer.class);

    public CreatedAddressConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<CreateAddressEvent> addressCreated() {
        return event -> {
            Address addressSearch = new Address(event.addressId(),  event.street(), event.houseNumber(), event.description(), event.isDefault());
            customerSearchService.addAddress(event.customerId(), addressSearch);
            LOGGER.info(String.format("Consumed address => %s", event.addressId()));
        };
    }

//    @KafkaListener(topics = "create-address", groupId = "create-address-group")
//    public void consume(CreateAddressEvent event){
//        LOGGER.info(String.format("Consumed address => %s", event.addressId()));
//        Address addressSearch = new Address(event.addressId(),  event.street(), event.houseNumber(), event.description(), event.isDefault());
//        customerSearchService.addAddress(event.customerId(), addressSearch);
//    }
}
