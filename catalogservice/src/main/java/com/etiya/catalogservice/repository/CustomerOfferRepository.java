package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.CustomerOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerOfferRepository extends JpaRepository<CustomerOffer, UUID> {
}