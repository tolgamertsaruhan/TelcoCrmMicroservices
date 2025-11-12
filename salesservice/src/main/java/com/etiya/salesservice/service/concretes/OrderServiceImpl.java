package com.etiya.salesservice.service.concretes;

import com.etiya.common.events.ClearBasketEvent;
import com.etiya.common.events.CreateProductEvent;
import com.etiya.salesservice.client.BasketServiceClient;
import com.etiya.salesservice.domain.Order;
import com.etiya.salesservice.domain.OrderItem;
import com.etiya.salesservice.repository.OrderRepository;
import com.etiya.salesservice.service.abstracts.OrderService;
import com.etiya.salesservice.service.requests.OrderItemConfiguration;
import com.etiya.salesservice.service.requests.OrderRequest;
import com.etiya.salesservice.service.responses.BasketItemResponse;
import com.etiya.salesservice.service.responses.BasketResponse;
import com.etiya.salesservice.transport.kafka.producer.basket.ClearBasketProducer;
import com.etiya.salesservice.transport.kafka.producer.product.CreateProductProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BasketServiceClient basketServiceClient;
    //private final CreateProductProducer createProductProducer;
    private final ClearBasketProducer clearBasketProducer;

    public OrderServiceImpl(OrderRepository orderRepository, BasketServiceClient basketServiceClient, ClearBasketProducer clearBasketProducer) {
        this.orderRepository = orderRepository;
        this.basketServiceClient = basketServiceClient;
        this.clearBasketProducer = clearBasketProducer;
    }

    @Override
    public void add(OrderRequest orderRequest) {

        // TODO: Bu alanda basketservice tarafına istek atılıp sepetteki veriyi sipariş tarafına göndermek
        // => basketserviceclient.getByCustomerId(customerId)



        //Todo: Sipariş onaylandıktan sonra basket service tarafına sepetin boşaltılması için event fırlatılacak.
        // orderRepository.save(order);
        // var basketClearEvent = new BasketClearEvent(order.CustomerId);
        // producer.send(basketClearEvent)

        BasketResponse basket = basketServiceClient.getByBillingAccountId(orderRequest.getBillingAccountId());
        if (basket == null || basket.getBasketItems().isEmpty()) {
            throw new RuntimeException("Sepet boş, sipariş oluşturulamaz.");
        }

        // 2️⃣ Order objesi oluştur
        Order order = new Order();
        order.setBillingAccountId(orderRequest.getBillingAccountId().toString());
        order.setAddressId(orderRequest.getAddressId().toString());
        order.setOrderDate(LocalDate.now());

        BigDecimal total = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        for (BasketItemResponse item : basket.getBasketItems()) {
            OrderItemConfiguration config = orderRequest.getConfigurations().stream()
                    .filter(c -> c.getProductOfferId().equals(item.getProductOfferId()))
                    .findFirst()
                    .orElse(null);

            OrderItem orderItem = new OrderItem();
            orderItem.setId(UUID.randomUUID().toString());
            orderItem.setProductOfferId(item.getProductOfferId().toString());
            orderItem.setProductName(item.getProductName());
            orderItem.setPrice(item.getPrice());
            orderItem.setDiscountedPrice(item.getDiscountedPrice());
            orderItem.setDiscountRate(item.getDiscountRate());
            orderItem.setProductId(UUID.randomUUID().toString());
            if (config != null) {
                orderItem.setConfigurationValues(config.getConfigurationValues());
            }

            BigDecimal priceToAdd = (item.getDiscountedPrice() != null && item.getDiscountedPrice().compareTo(BigDecimal.ZERO) > 0)
                    ? item.getDiscountedPrice()
                    : item.getPrice();

            total = total.add(priceToAdd);

            orderItems.add(orderItem);

            // 3️⃣ Ürün oluşturulması için ProductCreateEvent fırlat
            /*CreateProductEvent productEvent = new CreateProductEvent(
                    orderItem.getId(),
                    order.getBillingAccountId(),
                    orderItem.getProductOfferId(),
                    orderItem.getProductName(),
                    config != null ? config.getConfigurationValues() : Map.of()
            );*/
            //createProductProducer.produceProductCreated(productEvent);
        }

        order.setOrderItems(orderItems);
        order.setTotalPrice(total);
        orderRepository.save(order);

        // 4️⃣ Sepeti temizlemek için event fırlat
        ClearBasketEvent clearEvent = new ClearBasketEvent(order.getBillingAccountId());
        clearBasketProducer.produceBasketClear(clearEvent);

    }
}