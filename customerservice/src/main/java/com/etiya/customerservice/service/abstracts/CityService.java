package com.etiya.customerservice.service.abstracts;



import com.etiya.customerservice.domain.entities.City;
import com.etiya.customerservice.service.requests.city.CreateCityRequest;
import com.etiya.customerservice.service.requests.city.UpdateCityRequest;
import com.etiya.customerservice.service.responses.city.CreatedCityResponse;
import com.etiya.customerservice.service.responses.city.GetCityResponse;
import com.etiya.customerservice.service.responses.city.GetListCityResponse;
import com.etiya.customerservice.service.responses.city.UpdatedCityResponse;

import java.util.List;
import java.util.UUID;

public interface CityService {
    //void add(City city);
    //List<City> getAll();
    City existsById(UUID id);

    CreatedCityResponse add(CreateCityRequest request);
    UpdatedCityResponse update(UpdateCityRequest request);

    List<GetListCityResponse> getByNameContainsIgnoreCase(String pattern);
    List<GetListCityResponse> getCitiesWithoutDistrict();
    List<GetListCityResponse> getByNameStartingWith(String prefix);
    GetCityResponse getById(UUID id);
    List<GetListCityResponse> getAll();
    //List<GetListCityResponse> getAll();
    //GetCityResponse getById(int id);
    //List<GetListCityResponse> findAllByOrderByNameAsc();
    //List<GetListCityResponse> findAllByNameStartingPrefix(String prefix);

    //List<GetCityWithDistrictsResponse> findAllWithDistricts();

}
