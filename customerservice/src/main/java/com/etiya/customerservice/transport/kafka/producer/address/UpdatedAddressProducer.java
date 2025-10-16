package com.etiya.customerservice.transport.kafka.producer.address;


import com.etiya.common.events.UpdatedAddressEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class UpdatedAddressProducer {

    //private final KafkaTemplate<String, UpdatedAddressEvent> updateKafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAddressProducer.class);
    private final StreamBridge streamBridge;

    public UpdatedAddressProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }


    public void produceAddressUpdated(UpdatedAddressEvent event) {
        streamBridge.send("addressUpdated-out-0", event);
        LOGGER.info(String.format("Address updated event => %s", event.addressId()));
        //Message<UpdatedAddressEvent> message = MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC,"update-address").build();
        //updateKafkaTemplate.send(message);
    }

}
