package com.etiya.customerservice.service.requests.district;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDistrictRequest {
    private UUID id;
    private String name;
    private UUID cityId;
}
