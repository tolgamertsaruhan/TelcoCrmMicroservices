package com.etiya.searchservice.transport.kafka.billingaccount.consumer;

import com.etiya.common.events.DeletedAddressEvent;
import com.etiya.common.events.DeletedBillingAccountEvent;
import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.BillingAccount;
import com.etiya.searchservice.service.CustomerSearchService;
import com.etiya.searchservice.transport.kafka.address.consumer.DeletedAddressConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class DeletedBillingAccountConsumer {

    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(DeletedBillingAccountConsumer.class);


    public DeletedBillingAccountConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<DeletedBillingAccountEvent> billingAccountDeleted() {
        return event -> {
            BillingAccount billingAccountSearch = new BillingAccount(event.billingAccountId(),event.type(),event.status()
                    , event.accountNumber(),event.accountName(), event.customerId(), event.addressId(),event.deletedDate());
            customerSearchService.sofDeleteBillingAccount(billingAccountSearch);
            LOGGER.info(String.format("Deleted billingAccount => %s", event.billingAccountId()));
        };
    }
}
