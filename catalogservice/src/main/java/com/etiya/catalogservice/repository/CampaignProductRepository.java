package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.CampaignProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CampaignProductRepository extends JpaRepository<CampaignProduct, UUID> {
    CampaignProduct findByProduct_Id(UUID productId);
}