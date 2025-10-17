package com.etiya.customerservice.service.responses.city;



import com.etiya.customerservice.service.responses.district.GetDistrictResponse;

import java.util.List;
import java.util.UUID;

public class GetListCityResponse {

    private UUID id;
    private String name;

    public GetListCityResponse() {
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

    public GetListCityResponse(UUID id, String name, List<GetDistrictResponse> districts) {
        this.id = id;
        this.name = name;
        this.districts = districts;
    }

    private List<GetDistrictResponse> districts;
}
