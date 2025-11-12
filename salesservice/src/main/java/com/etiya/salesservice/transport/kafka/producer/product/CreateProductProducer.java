package com.etiya.salesservice.transport.kafka.producer.product;

import com.etiya.common.events.CreateAddressEvent;
import com.etiya.common.events.CreateProductEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

public class CreateProductProducer {

        //private final KafkaTemplate<String, CreateAddressEvent> kafkaTemplate;
        private final StreamBridge streamBridge;

        private static final Logger LOGGER = LoggerFactory.getLogger(CreateProductProducer.class);

        public CreateProductProducer(StreamBridge streamBridge) {
            this.streamBridge = streamBridge;
        }


        public void produceProductCreated(CreateProductEvent event) {
            streamBridge.send("productCreated-out-0", event);
            LOGGER.info(String.format("Product created event => %s", event.productId()));
            //Message<CreateAddressEvent> message = MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC,"create-address").build();
            //kafkaTemplate.send(message);
        }




}
