package com.etiya.customerservice.transport.kafka.producer.billingAccount;

import com.etiya.common.events.CreateAddressEvent;
import com.etiya.common.events.CreateBillingAccountEvent;
import com.etiya.customerservice.transport.kafka.producer.address.CreateAddressProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class CreateBillingAccountProducer {
    private final StreamBridge streamBridge;

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateBillingAccountProducer.class);


    public CreateBillingAccountProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }


    public void produceBillingAccountCreated(CreateBillingAccountEvent event) {
        streamBridge.send("billingAccountCreated-out-0", event);
        LOGGER.info(String.format("BillingAccount created event => %s", event.billingAccountId()));
        //Message<CreateAddressEvent> message = MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC,"create-address").build();
        //kafkaTemplate.send(message);
    }
}
