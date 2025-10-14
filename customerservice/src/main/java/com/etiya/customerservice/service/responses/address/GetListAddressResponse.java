package com.etiya.customerservice.service.responses.address;

import lombok.Data;

@Data
public class GetListAddressResponse {
    private int id;
    private String street;
    private String houseNumber;
    private String description;
    private boolean isDefault;
    private int districtId;
    private String districtName;
    private String cityName;
    private int customerId;

}
