package com.etiya.customerservice.repository;

import com.etiya.customerservice.domain.entities.Address;
import com.etiya.customerservice.domain.entities.ContactMedium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    // 1️ JPQL Query
    @Query("SELECT a FROM Address a WHERE a.isDefault = true")
    List<Address> findDefaultAddresses();

    // 2️ Native Query
    @Query(value = "SELECT * FROM addresses WHERE description LIKE %:keyword%", nativeQuery = true)
    List<Address> findByDescriptionNative(@Param("keyword") String keyword);

    // 3️ Derived Query (hazır method)
    List<Address> findByStreetContaining(String streetPart);

    void deleteById(UUID id);

    boolean existsByCustomerId(UUID customerId);

    List<Address> findByCustomerId(UUID id);
}
