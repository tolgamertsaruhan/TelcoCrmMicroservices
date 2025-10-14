package com.etiya.customerservice.service.requests.district;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDistrictRequest {
    private int id;
    private String name;
    private int cityId;
}
