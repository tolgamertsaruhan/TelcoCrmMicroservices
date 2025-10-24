package com.etiya.customerservice.service.abstracts;



import com.etiya.customerservice.service.requests.contactmedium.CreateContactMediumRequest;
import com.etiya.customerservice.service.requests.contactmedium.UpdateContactMediumRequest;
import com.etiya.customerservice.service.responses.contactmedium.CreatedContactMediumResponse;
import com.etiya.customerservice.service.responses.contactmedium.GetContactMediumResponse;
import com.etiya.customerservice.service.responses.contactmedium.GetListContactMediumResponse;
import com.etiya.customerservice.service.responses.contactmedium.UpdatedContactMediumResponse;

import java.util.List;
import java.util.UUID;

public interface ContactMediumService {
    CreatedContactMediumResponse add(CreateContactMediumRequest request);
    UpdatedContactMediumResponse update(UpdateContactMediumRequest request);
    List<GetListContactMediumResponse> getList();

    void delete(UUID id);

    void softDelete(UUID id);

    GetContactMediumResponse getById(UUID id);
    GetContactMediumResponse getByValue(String value);
    List<GetListContactMediumResponse> getListByType(String type);
    List<GetListContactMediumResponse> getListByCustomerId(UUID customerId);
}
