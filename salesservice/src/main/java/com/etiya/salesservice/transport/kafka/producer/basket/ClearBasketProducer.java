package com.etiya.salesservice.transport.kafka.producer.basket;

import com.etiya.common.events.ClearBasketEvent;
import com.etiya.common.events.CreateProductEvent;
import com.etiya.salesservice.transport.kafka.producer.product.CreateProductProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class ClearBasketProducer {
    //private final KafkaTemplate<String, CreateAddressEvent> kafkaTemplate;
    private final StreamBridge streamBridge;

    private static final Logger LOGGER = LoggerFactory.getLogger(ClearBasketProducer.class);

    public ClearBasketProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }


    public void produceBasketClear(ClearBasketEvent event) {
        streamBridge.send("basketClear-out-0", event);
        LOGGER.info(String.format("Basket Clear event => %s", event.billingAccountId()));
        //Message<CreateAddressEvent> message = MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC,"create-address").build();
        //kafkaTemplate.send(message);
    }
}
