package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.ProductSpecification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductSpecificationRepository extends JpaRepository<ProductSpecification, UUID> {
}
