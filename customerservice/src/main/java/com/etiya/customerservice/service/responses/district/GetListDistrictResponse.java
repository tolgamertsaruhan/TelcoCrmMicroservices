package com.etiya.customerservice.service.responses.district;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetListDistrictResponse {

    private int id;
    private String name;
    private int cityId;
    private List<Integer> addresses;
}
