package com.etiya.searchservice.transport.kafka.billingaccount.consumer;

import com.etiya.common.events.CreateAddressEvent;
import com.etiya.common.events.CreateBillingAccountEvent;
import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.BillingAccount;
import com.etiya.searchservice.service.CustomerSearchService;
import com.etiya.searchservice.transport.kafka.address.consumer.CreatedAddressConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
@Configuration
public class CreatedBillingAccountConsumer {

    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(CreatedBillingAccountConsumer.class);



    public CreatedBillingAccountConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<CreateBillingAccountEvent> billingAccountCreated() {
        return event -> {
            BillingAccount billingAccountSearch = new BillingAccount(event.billingAccountId(),event.type(),event.status()
                    , event.accountNumber(),event.accountName(), event.customerId(), event.addressId());
            customerSearchService.addBillingAccount(billingAccountSearch);
            LOGGER.info(String.format("Consumed billingAccount => %s", event.billingAccountId()));
        };
    }
}
