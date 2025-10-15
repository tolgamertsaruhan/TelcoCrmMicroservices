package com.etiya.customerservice.service.responses.district;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class GetListDistrictResponse {

    private UUID id;
    private String name;
    private UUID cityId;
    private List<UUID> addresses;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getCityId() {
        return cityId;
    }

    public void setCityId(UUID cityId) {
        this.cityId = cityId;
    }

    public List<UUID> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<UUID> addresses) {
        this.addresses = addresses;
    }

    public GetListDistrictResponse() {
    }

    public GetListDistrictResponse(UUID id, String name, UUID cityId, List<UUID> addresses) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
        this.addresses = addresses;
    }
}
