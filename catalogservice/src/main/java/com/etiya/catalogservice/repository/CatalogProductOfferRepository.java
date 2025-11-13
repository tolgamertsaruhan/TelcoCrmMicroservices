package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.CatalogProductOffer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CatalogProductOfferRepository extends JpaRepository<CatalogProductOffer, UUID> {
//    @EntityGraph(attributePaths = {"catalog"})
    List<CatalogProductOffer> findAllByCatalog_Id(UUID catalogId);

    @Query("SELECT cpo FROM CatalogProductOffer cpo " +
            "WHERE cpo.catalog.id = :catalogId " +
            "AND LOWER(cpo.productOffer.name) LIKE LOWER(CONCAT('%', :productOfferName, '%'))")
    List<CatalogProductOffer> searchByCatalogIdAndProductOfferName(
            @Param("catalogId") UUID catalogId,
            @Param("productOfferName") String productOfferName
    );
}