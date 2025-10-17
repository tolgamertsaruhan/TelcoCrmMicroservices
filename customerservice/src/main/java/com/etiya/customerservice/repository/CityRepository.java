package com.etiya.customerservice.repository;


import com.etiya.customerservice.domain.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City, UUID> {

    boolean existsById(UUID id);

    boolean existsByNameIgnoreCase(String name);

    List<City> findCityByNameContainsIgnoreCase(String pattern);

    @Query("SELECT c FROM City c LEFT JOIN c.districts d WHERE d IS NULL")
    List<City> findCitiesWithoutDistrict();

    @Query(value = "SELECT * FROM cities c WHERE c.name LIKE :prefix%",nativeQuery = true)
    List<City> findCitiesByNameStartingWith(String prefix);

    boolean existsCityByName(String name);
}
