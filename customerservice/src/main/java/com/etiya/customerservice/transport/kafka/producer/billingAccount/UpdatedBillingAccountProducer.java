package com.etiya.customerservice.transport.kafka.producer.billingAccount;

import com.etiya.common.events.UpdatedAddressEvent;
import com.etiya.common.events.UpdatedBillingAccountEvent;
import com.etiya.customerservice.transport.kafka.producer.address.UpdatedAddressProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class UpdatedBillingAccountProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdatedBillingAccountProducer.class);
    private final StreamBridge streamBridge;


    public UpdatedBillingAccountProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }


    public void produceBillingAccountUpdated(UpdatedBillingAccountEvent event) {
        streamBridge.send("billingAccountUpdated-out-0", event);
        LOGGER.info(String.format("BillingAccount updated event => %s", event.billingAccountId()));
        //Message<UpdatedAddressEvent> message = MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC,"update-address").build();
        //updateKafkaTemplate.send(message);
    }
}
