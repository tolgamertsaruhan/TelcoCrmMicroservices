package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.ProductSpecCharacteristic;
import com.etiya.catalogservice.service.requests.productSpecCharacteristic.CreateProductSpecCharacteristicRequest;
import com.etiya.catalogservice.service.requests.productSpecCharacteristic.UpdateProductSpecCharacteristicRequest;
import com.etiya.catalogservice.service.responses.productSpecCharacteristic.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductSpecCharacteristicMapper {
    ProductSpecCharacteristicMapper INSTANCE = Mappers.getMapper(ProductSpecCharacteristicMapper.class);

    @Mapping(target = "productSpecification.id", source = "productSpecificationId")
    @Mapping(target = "characteristic.id", source = "characteristicId")
    ProductSpecCharacteristic productSpecCharacteristicFromCreateProductSpecCharacteristicRequest(CreateProductSpecCharacteristicRequest request);

    @Mapping(target = "productSpecification.id", source = "productSpecificationId")
    @Mapping(target = "characteristic.id", source = "characteristicId")
    ProductSpecCharacteristic productSpecCharacteristicFromUpdateProductSpecCharacteristicRequest(UpdateProductSpecCharacteristicRequest request);

    void updateProductSpecCharacteristicFromRequest(UpdateProductSpecCharacteristicRequest request,
                                                    @MappingTarget ProductSpecCharacteristic entity);

    @Mapping(target = "productSpecificationId", source = "productSpecification.id")
    @Mapping(target = "characteristicId", source = "characteristic.id")
    CreatedProductSpecCharacteristicResponse createdResponseFromEntity(ProductSpecCharacteristic entity);

    @Mapping(target = "productSpecificationId", source = "productSpecification.id")
    @Mapping(target = "characteristicId", source = "characteristic.id")
    UpdatedProductSpecCharacteristicResponse updatedResponseFromEntity(ProductSpecCharacteristic entity);

    @Mapping(target = "productSpecificationId", source = "productSpecification.id")
    @Mapping(target = "characteristicId", source = "characteristic.id")
    GetProductSpecCharacteristicResponse getResponseFromEntity(ProductSpecCharacteristic entity);

    @Mapping(target = "productSpecificationId", source = "productSpecification.id")
    @Mapping(target = "characteristicId", source = "characteristic.id")
    List<GetListProductSpecCharacteristicResponse> getListResponseFromEntityList(List<ProductSpecCharacteristic> entities);

    @Mapping(target = "productSpecificationId", source = "productSpecification.id")
    @Mapping(target = "characteristicId", source = "characteristic.id")
    CreatedProductSpecCharacteristicResponse createdProductSpecCharacteristicResponseFromProductSpecCharacteristic(ProductSpecCharacteristic entity);
}
