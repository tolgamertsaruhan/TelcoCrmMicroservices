package com.etiya.customerservice.service.responses.district;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetDistrictResponse {
    private UUID id;
    private String name;
    private UUID cityId;
    private List<UUID> addresses;
}
