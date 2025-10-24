package com.etiya.customerservice.transport.kafka.producer.contactMedium;

import com.etiya.common.events.UpdateContactMediumEvent;
import com.etiya.common.events.UpdatedAddressEvent;
import com.etiya.customerservice.transport.kafka.producer.address.CreateAddressProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class UpdateContactMediumProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateContactMediumProducer.class);
    private final StreamBridge streamBridge;

    public UpdateContactMediumProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }


    public void produceContactMediumUpdated(UpdateContactMediumEvent event) {
        streamBridge.send("contactMediumUpdated-out-0", event);
        LOGGER.info(String.format("ContactMedium updated event => %s", event.contactMediumId()));
    }
}
