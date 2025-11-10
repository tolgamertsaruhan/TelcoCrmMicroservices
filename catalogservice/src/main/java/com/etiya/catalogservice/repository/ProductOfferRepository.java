package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.ProductOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductOfferRepository extends JpaRepository<ProductOffer, UUID> {
    //List<ProductOffer> findByProductId(UUID productId);
    ProductOffer findByIdAndDeletedDateIsNull(UUID id);
    void deleteById(UUID id);
    List<ProductOffer> findByDeletedDateIsNull();
}