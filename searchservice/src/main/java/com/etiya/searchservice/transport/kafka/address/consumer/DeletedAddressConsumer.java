package com.etiya.searchservice.transport.kafka.address.consumer;

import com.etiya.common.events.CreateAddressEvent;
import com.etiya.common.events.DeletedAddressEvent;
import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Configuration
public class DeletedAddressConsumer {

    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(DeletedAddressConsumer.class);

    public DeletedAddressConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;

    }

    @Bean
    public Consumer<DeletedAddressEvent> addressDeleted() {
        return event -> {
            Address addressSearch = new Address(event.addressId(), event.customerId(), event.districtName(), event.cityName(), event.street(), event.houseNumber(), event.description(), event.isDefault(),event.deletedDate());
            customerSearchService.sofDeleteAddress(addressSearch);
            LOGGER.info(String.format("Deleted address => %s", event.addressId()));
        };
    }

//    @KafkaListener(topics = "delete-address", groupId = "delete-address-group")
//    public void consume(DeletedAddressEvent event){
//        LOGGER.info(String.format("Deleted address => %s", event.addressId()));
//        customerSearchService.deleteAddress(event.customerId(), event.addressId());
//    }
}
