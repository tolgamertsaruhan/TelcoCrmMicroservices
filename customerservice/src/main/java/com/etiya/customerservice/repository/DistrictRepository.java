package com.etiya.customerservice.repository;

import com.etiya.customerservice.domain.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DistrictRepository extends JpaRepository<District, UUID> {

    List<District> findDistrictByName(String name);

    //JPQL Query
    @Query("SELECT d FROM District d WHERE d.name LIKE :name%")
    List<District> findByNameLike(@Param("name") String name);

    //Native Query
    @Query(value = "select * from districts d where d.city_id = :cityId",nativeQuery = true)
    List<District> findByCityId(@Param("cityId") UUID cityId);

    void deleteById(UUID id);

}
