package com.etiya.customerservice.service.responses.city;



import com.etiya.customerservice.service.responses.district.GetDistrictResponse;

import java.util.List;
import java.util.UUID;


public class GetCityResponse {

    private UUID id;

    public GetCityResponse() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GetDistrictResponse> getDistricts() {
        return districts;
    }

    public void setDistricts(List<GetDistrictResponse> districts) {
        this.districts = districts;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public GetCityResponse(UUID id, String name, List<GetDistrictResponse> districts) {
        this.id = id;
        this.name = name;
        this.districts = districts;
    }

    private String name;
    private List<GetDistrictResponse> districts;
}
