package com.etiya.customerservice.repository;


import com.etiya.customerservice.domain.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City, UUID> {

    boolean existsById(UUID id);

}
