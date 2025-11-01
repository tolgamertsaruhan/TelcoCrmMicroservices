package com.etiya.customerservice.transport.kafka.producer.billingAccount;

import com.etiya.common.events.DeletedAddressEvent;
import com.etiya.common.events.DeletedBillingAccountEvent;
import com.etiya.customerservice.transport.kafka.producer.address.DeletedAddressProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class DeletedBillingAccountProducer {

    private final StreamBridge streamBridge;

    private static final Logger LOGGER = LoggerFactory.getLogger(DeletedBillingAccountProducer.class);



    public DeletedBillingAccountProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void produceBillingAccountDeleted(DeletedBillingAccountEvent event) {
        streamBridge.send("billingAccountDeleted-out-0", event);
        LOGGER.info(String.format("BillingAccount deleted event => %s", event.billingAccountId()));
        //Message<DeletedAddressEvent> message = MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC,"delete-address").build();
        //deleteKafkaTemplate.send(message);
    }
}
