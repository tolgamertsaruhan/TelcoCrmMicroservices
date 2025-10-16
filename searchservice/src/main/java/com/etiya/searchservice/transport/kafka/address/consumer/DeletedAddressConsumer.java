package com.etiya.searchservice.transport.kafka.address.consumer;

import com.etiya.common.events.CreateAddressEvent;
import com.etiya.common.events.DeletedAddressEvent;
import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DeletedAddressConsumer {

    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(UpdatedAddressConsumer.class);

    public DeletedAddressConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;

    }

    @KafkaListener(topics = "delete-address", groupId = "delete-address-group")
    public void consume(DeletedAddressEvent event){
        LOGGER.info(String.format("Deleted address => %s", event.addressId()));
        customerSearchService.deleteAddress(event.customerId(), event.addressId());
    }
}
