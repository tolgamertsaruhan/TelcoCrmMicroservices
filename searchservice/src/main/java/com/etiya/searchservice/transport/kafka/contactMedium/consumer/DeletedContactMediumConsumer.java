package com.etiya.searchservice.transport.kafka.contactMedium.consumer;

import com.etiya.common.events.DeleteContactMediumEvent;
import com.etiya.common.events.DeletedAddressEvent;
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
public class DeletedContactMediumConsumer {
    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(DeletedContactMediumConsumer.class);

    public DeletedContactMediumConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;

    }

    @Bean
    public Consumer<DeleteContactMediumEvent> contactMediumDeleted() {
        return event -> {
            ContactMedium contactMediumSearch = new ContactMedium(event.contactMediumId(), event.customerId(), event.type(), event.value(),event.isPrimary(), event.deletedDate());
            customerSearchService.softDeleteContactMedium(contactMediumSearch);
            LOGGER.info(String.format("Deleted contactMedium => %s", event.contactMediumId()));
        };
    }
}
