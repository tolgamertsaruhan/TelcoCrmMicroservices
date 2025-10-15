package com.etiya.customerservice.service.abstracts;



import com.etiya.customerservice.service.requests.district.CreateDistrictRequest;
import com.etiya.customerservice.service.requests.district.UpdateDistrictRequest;
import com.etiya.customerservice.service.responses.district.CreatedDistrictResponse;
import com.etiya.customerservice.service.responses.district.GetDistrictResponse;
import com.etiya.customerservice.service.responses.district.GetListDistrictResponse;
import com.etiya.customerservice.service.responses.district.UpdatedDistrictResponse;

import java.util.List;
import java.util.UUID;

public interface DistrictService {
    CreatedDistrictResponse add(CreateDistrictRequest request);
    List<GetListDistrictResponse> getAll();
    UpdatedDistrictResponse update(UpdateDistrictRequest request);
    GetDistrictResponse getById(UUID id);
    void deleteById(UUID id);
    List<GetListDistrictResponse> getByName(String name);
    List<GetListDistrictResponse> getByNameStartingWith(String name);
    List<GetListDistrictResponse> getByCityId(UUID cityId);
}
