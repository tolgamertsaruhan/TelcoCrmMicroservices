package com.etiya.basketservice.transport.kafka.basket.consumer;

import com.etiya.basketservice.domain.Basket;
import com.etiya.basketservice.service.abstracts.BasketService;
import com.etiya.common.events.ClearBasketEvent;
import com.etiya.common.events.DeletedAddressEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.function.Consumer;

@Configuration
public class ClearBasketConsumer {
    private final BasketService basketService;
    private final Logger LOGGER = LoggerFactory.getLogger(ClearBasketConsumer.class);

    public ClearBasketConsumer(BasketService basketService) {
        this.basketService = basketService;
    }

    @Bean
    public Consumer<ClearBasketEvent> basketClear() {
        return event -> {
            UUID uuid = UUID.fromString(event.billingAccountId());
            Basket basket = new Basket(uuid);
            basketService.clearBasket(uuid);
            LOGGER.info(String.format("Clear basket => %s", event.billingAccountId()));
        };
    }
}
