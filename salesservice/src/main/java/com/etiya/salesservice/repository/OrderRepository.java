package com.etiya.salesservice.repository;

import com.etiya.salesservice.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order,String> {
//    BillingAccountId'ye göre tüm order'ları döndür
    List<Order> findByBillingAccountId(String billingAccountId);
}