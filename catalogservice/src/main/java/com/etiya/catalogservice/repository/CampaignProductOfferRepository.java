package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.CampaignProductOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CampaignProductOfferRepository extends JpaRepository<CampaignProductOffer, UUID> {
    CampaignProductOffer findByProductOffer_Id(UUID productOfferId);
}