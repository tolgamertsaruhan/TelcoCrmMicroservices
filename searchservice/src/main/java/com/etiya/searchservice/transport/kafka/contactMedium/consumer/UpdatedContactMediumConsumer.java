package com.etiya.searchservice.transport.kafka.contactMedium.consumer;

import com.etiya.common.events.UpdateContactMediumEvent;
import com.etiya.common.events.UpdatedAddressEvent;
import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.ContactMedium;
import com.etiya.searchservice.service.CustomerSearchService;
import com.etiya.searchservice.transport.kafka.address.consumer.UpdatedAddressConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class UpdatedContactMediumConsumer {
    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(UpdatedContactMediumConsumer.class);

    public UpdatedContactMediumConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;

    }

    @Bean
    public Consumer<UpdateContactMediumEvent> contactMediumUpdated() {
        return event -> {
            ContactMedium contactMediumSearch = new ContactMedium(event.contactMediumId(), event.customerId(), event.type(), event.value(), event.isPrimary());
            customerSearchService.updateContactMedium(contactMediumSearch);
            LOGGER.info(String.format("Updated contactMedium => %s", event.contactMediumId()));
        };
    }
}
