package com.etiya.customerservice.transport.kafka.producer.customer;

import com.etiya.common.events.UpdatedAddressEvent;
import com.etiya.common.events.UpdatedIndividualCustomerEvent;
import com.etiya.customerservice.transport.kafka.producer.address.UpdatedAddressProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class UpdateCustomerProducer {

    //private final KafkaTemplate<String, UpdatedAddressEvent> updateKafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateCustomerProducer.class);
    private final StreamBridge streamBridge;

    public UpdateCustomerProducer(StreamBridge streamBridge)
    {
        this.streamBridge = streamBridge;
    }


    public void produceCustomerUpdated(UpdatedIndividualCustomerEvent event) {
        streamBridge.send("customerUpdated-out-0", event);
        LOGGER.info(String.format("Customer updated event => %s", event.customerId()));
        //Message<UpdatedAddressEvent> message = MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC,"update-address").build();
        //updateKafkaTemplate.send(message);
    }
}
