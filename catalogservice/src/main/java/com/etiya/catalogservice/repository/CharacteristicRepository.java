package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CharacteristicRepository extends JpaRepository<Characteristic, UUID> {
}