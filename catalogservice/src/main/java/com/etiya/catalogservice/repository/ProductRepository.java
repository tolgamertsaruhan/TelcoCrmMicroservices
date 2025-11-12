package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
//    @EntityGraph(attributePaths = {"catalog", "productSpecification"})
//    List<Product> findAllByCatalog_IdAndDeletedDateIsNull(UUID catalogId);
//
//    @EntityGraph(attributePaths = {"catalog", "productSpecification"})
//    List<Product> findAllByProductSpecification_IdAndDeletedDateIsNull(UUID specificationId);
}
