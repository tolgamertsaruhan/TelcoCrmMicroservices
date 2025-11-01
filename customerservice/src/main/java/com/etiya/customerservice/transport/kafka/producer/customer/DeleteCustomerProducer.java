package com.etiya.customerservice.transport.kafka.producer.customer;

import com.etiya.common.events.DeleteContactMediumEvent;
import com.etiya.common.events.DeletedCustomerEvent;
import com.etiya.customerservice.transport.kafka.producer.contactMedium.DeleteContactMediumProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class DeleteCustomerProducer {
    private final StreamBridge streamBridge;

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteCustomerProducer.class);



    public DeleteCustomerProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void produceCustomerDeleted(DeletedCustomerEvent event) {
        streamBridge.send("customerDeleted-out-0", event);
        LOGGER.info(String.format("Customer deleted event => %s", event.customerId()));
    }
}
