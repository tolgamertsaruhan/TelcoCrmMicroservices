package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.CampaignProductOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CampaignProductOfferRepository extends JpaRepository<CampaignProductOffer, UUID> {
    CampaignProductOffer findByProductOffer_Id(UUID productOfferId);

    List<CampaignProductOffer> findByCampaign_Id(UUID campaignId);

    @Query("SELECT cpo FROM CampaignProductOffer cpo " +
            "WHERE cpo.campaign.id = :campaignId " +
            "AND LOWER(cpo.productOffer.name) LIKE LOWER(CONCAT('%', :productOfferName, '%'))")
    List<CampaignProductOffer> searchByCampaignIdAndProductOfferName(
            @Param("campaignId") UUID campaignId,
            @Param("productOfferName") String productOfferName
    );
}