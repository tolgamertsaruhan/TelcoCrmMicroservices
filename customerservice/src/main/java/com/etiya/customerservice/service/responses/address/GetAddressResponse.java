package com.etiya.customerservice.service.responses.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAddressResponse {
    private UUID id;
    private String street;
    private String houseNumber;
    private String description;
    private boolean isDefault;
    private UUID districtId;
    private UUID customerId;
    private String districtName;
    private String cityName;
    private String customerFirstName;
}
