package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.requests.productSpecCharacteristic.CreateProductSpecCharacteristicRequest;
import com.etiya.catalogservice.service.requests.productSpecCharacteristic.UpdateProductSpecCharacteristicRequest;
import com.etiya.catalogservice.service.responses.productSpecCharacteristic.*;

import java.util.List;
import java.util.UUID;

public interface ProductSpecCharacteristicService {

    CreatedProductSpecCharacteristicResponse add(CreateProductSpecCharacteristicRequest request);

    UpdatedProductSpecCharacteristicResponse update(UpdateProductSpecCharacteristicRequest request);

    void softDelete(UUID id);

    void delete(UUID id);

    List<GetListProductSpecCharacteristicResponse> getAll();

    GetProductSpecCharacteristicResponse getById(UUID id);

    List<GetListProductSpecCharacteristicResponse> getByProductSpecificationId(UUID specId);

    List<GetListProductSpecCharacteristicResponse> getByCharacteristicId(UUID characteristicId);
}
