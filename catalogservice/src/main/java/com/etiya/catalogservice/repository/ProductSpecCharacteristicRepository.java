package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.ProductSpecCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductSpecCharacteristicRepository extends JpaRepository<ProductSpecCharacteristic, UUID> {

    List<ProductSpecCharacteristic> findByProductSpecification_Id(UUID productSpecificationId);

    List<ProductSpecCharacteristic> findByCharacteristic_Id(UUID characteristicId);
}
