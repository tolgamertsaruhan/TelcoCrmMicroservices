package com.etiya.customerservice.transport.kafka.producer.contactMedium;

import com.etiya.common.events.DeleteContactMediumEvent;
import com.etiya.common.events.DeletedAddressEvent;
import com.etiya.customerservice.transport.kafka.producer.address.DeletedAddressProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class DeleteContactMediumProducer {

    //private final KafkaTemplate<String, DeletedAddressEvent> deleteKafkaTemplate;
    private final StreamBridge streamBridge;

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteContactMediumProducer.class);

    public DeleteContactMediumProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void produceContactMediumDeleted(DeleteContactMediumEvent event) {
        streamBridge.send("contactMediumDeleted-out-0", event);
        LOGGER.info(String.format("ContactMedium deleted event => %s", event.contactMediumId()));
    }
}
