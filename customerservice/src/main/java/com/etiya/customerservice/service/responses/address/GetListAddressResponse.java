package com.etiya.customerservice.service.responses.address;

import lombok.Data;

import java.util.UUID;

@Data
public class GetListAddressResponse {
    private UUID id;
    private String street;
    private String houseNumber;
    private String description;
    private boolean isDefault;
    private UUID districtId;
    private String districtName;
    private String cityName;
    private UUID customerId;

}
