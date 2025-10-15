package com.etiya.customerservice.service.concretes;


import com.etiya.customerservice.domain.entities.District;
import com.etiya.customerservice.repository.DistrictRepository;
import com.etiya.customerservice.service.abstracts.CityService;
import com.etiya.customerservice.service.abstracts.DistrictService;
import com.etiya.customerservice.service.mappings.DistrictMapper;
import com.etiya.customerservice.service.requests.district.CreateDistrictRequest;
import com.etiya.customerservice.service.requests.district.UpdateDistrictRequest;
import com.etiya.customerservice.service.responses.district.CreatedDistrictResponse;
import com.etiya.customerservice.service.responses.district.GetDistrictResponse;
import com.etiya.customerservice.service.responses.district.GetListDistrictResponse;
import com.etiya.customerservice.service.responses.district.UpdatedDistrictResponse;
import com.etiya.customerservice.service.rules.DistrictBusinessRoles;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final DistrictBusinessRoles districtBusinessRoles;
    private final CityService cityService;

    public DistrictServiceImpl(DistrictRepository districtRepository, DistrictBusinessRoles districtBusinessRoles, CityService cityService) {
        this.districtRepository = districtRepository;
        this.districtBusinessRoles = districtBusinessRoles;
        this.cityService = cityService;
    }

    @Override
    public CreatedDistrictResponse add(CreateDistrictRequest request) {
        cityService.existsById(request.getCityId());
        District district = DistrictMapper.INSTANCE.districtFromCreateDistrictRequest(request);
        District createdDistrict = districtRepository.save(district);
        CreatedDistrictResponse response = DistrictMapper.INSTANCE.createdDistrictResponseFromDistrict(createdDistrict);
        return response;
    }

    @Override
    public List<GetListDistrictResponse> getAll() {
        List<District> districts = districtRepository.findAll();
        for (District district : districts) {
            System.out.println(district.getCity().getName());
        }
        List<GetListDistrictResponse> responses = DistrictMapper.INSTANCE.getListDistrictResponseFromDistrict(districts);
        for (GetListDistrictResponse response : responses) {
            System.out.println(response.getCityId());
        }
        return responses;
    }

    @Override
    public UpdatedDistrictResponse update(UpdateDistrictRequest request) {
        District oldDistrict = districtRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Address not found"));

        District district = DistrictMapper.INSTANCE.districtFromUpdateDistrictRequest(request, oldDistrict);
        District updatedDistrict = districtRepository.save(district);

        UpdatedDistrictResponse response = DistrictMapper.INSTANCE.updatedDistrictResponseFromDistrict(updatedDistrict);
        return response;
    }



    @Override
    public GetDistrictResponse getById(UUID id) {
        District district = districtRepository.findById(id).orElseThrow(() -> new RuntimeException("District not found"));
        GetDistrictResponse response = DistrictMapper.INSTANCE.getDistrictResponseFromDistrict(district);
        return response;
    }

    @Override
    public void deleteById(UUID id) {
        districtBusinessRoles.checkIfAddressExists(id);
        districtRepository.deleteById(id);
    }

    @Override
    public List<GetListDistrictResponse> getByName(String name) {
        List<District> districts = districtRepository.findDistrictByName(name);
        List<GetListDistrictResponse> responses = DistrictMapper.INSTANCE.getListDistrictResponseFromDistrict(districts);
        return responses;
    }

    @Override
    public List<GetListDistrictResponse> getByNameStartingWith(String name) {
        List<District> districts = districtRepository.findByNameLike(name);

        List<GetListDistrictResponse> responses = DistrictMapper.INSTANCE.getListDistrictResponseFromDistrict(districts);
        return responses;
    }

    @Override
    public List<GetListDistrictResponse> getByCityId(UUID cityId) {
        List<District> districts = districtRepository.findByCityId(cityId);

        List<GetListDistrictResponse> responses = DistrictMapper.INSTANCE.getListDistrictResponseFromDistrict(districts);
        return responses;
    }
}
