package com.etiya.searchservice.transport.kafka.contactMedium.consumer;

import com.etiya.common.events.CreateAddressEvent;
import com.etiya.common.events.CreateContactMediumEvent;
import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.ContactMedium;
import com.etiya.searchservice.service.CustomerSearchService;
import com.etiya.searchservice.transport.kafka.address.consumer.CreatedAddressConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class CreatedContactMediumConsumer {

    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(CreatedContactMediumConsumer.class);

    public CreatedContactMediumConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<CreateContactMediumEvent> contactMediumCreated() {
        return event -> {
            ContactMedium contactMediumSearch = new ContactMedium(event.contactMediumId(),event.customerId(),event.type(), event.value(),event.isPrimary());
            customerSearchService.addContactMedium(contactMediumSearch);
            LOGGER.info(String.format("Consumed contactMedium => %s", event.contactMediumId()));
        };
    }

}
