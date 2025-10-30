package com.etiya.customerservice.service.concretes;


import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.customerservice.domain.entities.City;
import com.etiya.customerservice.domain.entities.District;
import com.etiya.customerservice.repository.CityRepository;
import com.etiya.customerservice.repository.DistrictRepository;
import com.etiya.customerservice.service.abstracts.CityService;
import com.etiya.customerservice.service.abstracts.DistrictService;
import com.etiya.customerservice.service.mappings.AddressMapper;
import com.etiya.customerservice.service.mappings.CityMapper;
import com.etiya.customerservice.service.requests.city.CreateCityRequest;
import com.etiya.customerservice.service.requests.city.UpdateCityRequest;
import com.etiya.customerservice.service.responses.city.CreatedCityResponse;
import com.etiya.customerservice.service.responses.city.GetCityResponse;
import com.etiya.customerservice.service.responses.city.GetListCityResponse;
import com.etiya.customerservice.service.responses.city.UpdatedCityResponse;
import com.etiya.customerservice.service.rules.CityBusinessRules;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityBusinessRules cityBusinessRules;

    private final DistrictRepository districtRepository;



    public CityServiceImpl(CityRepository cityRepository, CityBusinessRules cityBusinessRules, DistrictRepository districtRepository) {
        this.cityRepository = cityRepository;
        this.cityBusinessRules = cityBusinessRules;
        this.districtRepository = districtRepository;

    }

    @Override
    public CreatedCityResponse add(CreateCityRequest request) {
        cityBusinessRules.checkExistsName(request.getName());
        City city = CityMapper.INSTANCE.cityFromCreateCityRequest(request);
        City createdCity = cityRepository.save(city);

        CreatedCityResponse response = CityMapper.INSTANCE.createdCityResponseFromCity(createdCity);

        return response;
    }

    @Override
    public UpdatedCityResponse update(UpdateCityRequest request) {
        City city = cityRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("City not found"));

        City mappedCity = CityMapper.INSTANCE.cityFromUpdateCityRequest(request, city);
        City updatedCity = cityRepository.save(mappedCity);
        UpdatedCityResponse response = CityMapper.INSTANCE.updatedCityResponseFromCity(updatedCity);
        return response;
    }


    @Override
    public City existsById(UUID id) {
        cityBusinessRules.checkIfCityExists(id);
        return cityRepository.findById(id).get();
    }

    @Override
    public List<GetListCityResponse> getByNameContainsIgnoreCase(String pattern) {
        List<City> cities = cityRepository.findCityByNameContainsIgnoreCase(pattern);
        List<GetListCityResponse> response = CityMapper.INSTANCE.getListCityResponseFromCity(cities);
        return response;
    }

    @Override
    public List<GetListCityResponse> getCitiesWithoutDistrict() {
        List<City> cities = cityRepository.findCitiesWithoutDistrict();
        List<GetListCityResponse> response = CityMapper.INSTANCE.getListCityResponseFromCity(cities);
        return response;
    }

    @Override
    public List<GetListCityResponse> getByNameStartingWith(String prefix) {
        List<City> cities = cityRepository.findCitiesByNameStartingWith(prefix);
        List<GetListCityResponse> response = CityMapper.INSTANCE.getListCityResponseFromCity(cities);
        return response;
    }

    @Override
    public GetCityResponse getById(UUID id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new BusinessException("City not found"));
        GetCityResponse response = CityMapper.INSTANCE.getCityResponseFromCity(city);
        return response;
    }

    @Override
    public List<GetListCityResponse> getAll() {
        List<City> cities = cityRepository.findAll();

        List<GetListCityResponse> response = CityMapper.INSTANCE.getListCityResponseFromCity(cities);
        return response;
    }


}
