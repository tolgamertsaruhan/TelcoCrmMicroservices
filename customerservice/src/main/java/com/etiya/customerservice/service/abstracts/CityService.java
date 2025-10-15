package com.etiya.customerservice.service.abstracts;



import com.etiya.customerservice.domain.entities.City;

import java.util.List;
import java.util.UUID;

public interface CityService {
    void add(City city);
    List<City> getAll();
    City existsById(UUID id);
}
