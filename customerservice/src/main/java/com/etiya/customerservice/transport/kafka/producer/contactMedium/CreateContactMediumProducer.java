package com.etiya.customerservice.transport.kafka.producer.contactMedium;

import com.etiya.common.events.CreateAddressEvent;
import com.etiya.common.events.CreateContactMediumEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class CreateContactMediumProducer {

    //private final KafkaTemplate<String, CreateAddressEvent> kafkaTemplate;
    private final StreamBridge streamBridge;

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateContactMediumProducer.class);

    public CreateContactMediumProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }


    public void produceContactMediumCreated(CreateContactMediumEvent event) {
        streamBridge.send("contactMediumCreated-out-0", event);
        LOGGER.info(String.format("ContactMedium created event => %s", event.contactMediumId()));
    }

}
