package com.etiya.searchservice.transport.kafka.billingaccount.consumer;

import com.etiya.common.events.UpdatedAddressEvent;
import com.etiya.common.events.UpdatedBillingAccountEvent;
import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.BillingAccount;
import com.etiya.searchservice.service.CustomerSearchService;
import com.etiya.searchservice.transport.kafka.address.consumer.UpdatedAddressConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class UpdatedBillingAccountConsumer {

    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(UpdatedBillingAccountConsumer.class);



    public UpdatedBillingAccountConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<UpdatedBillingAccountEvent> billingAccountUpdated() {
        return event -> {
            BillingAccount billingAccountSearch = new BillingAccount(event.billingAccountId(),event.type(),event.status()
                    , event.accountNumber(),event.accountName(), event.customerId(), event.addressId());
            customerSearchService.updateBillingAccount(billingAccountSearch);
            LOGGER.info(String.format("Updated billingAccount => %s", event.billingAccountId()));

        };
    }
}
