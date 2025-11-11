package com.etiya.catalogservice.repository;


import com.etiya.catalogservice.domain.entities.ProdCharValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdCharValueRepository extends JpaRepository<ProdCharValue, UUID> {
}