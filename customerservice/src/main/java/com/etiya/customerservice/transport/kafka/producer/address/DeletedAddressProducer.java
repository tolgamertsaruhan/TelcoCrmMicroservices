package com.etiya.customerservice.transport.kafka.producer.address;

import com.etiya.common.events.CreateAddressEvent;
import com.etiya.common.events.DeletedAddressEvent;
import com.etiya.common.events.UpdatedAddressEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class DeletedAddressProducer {

    private final KafkaTemplate<String, DeletedAddressEvent> deleteKafkaTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAddressProducer.class);

    public DeletedAddressProducer(KafkaTemplate<String, DeletedAddressEvent> deleteKafkaTemplate) {
        this.deleteKafkaTemplate = deleteKafkaTemplate;
    }


    public void produceAddressDeleted(DeletedAddressEvent event) {
        LOGGER.info(String.format("Address deleted event => %s", event.addressId()));
        Message<DeletedAddressEvent> message = MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC,"delete-address").build();
        deleteKafkaTemplate.send(message);
    }
}
