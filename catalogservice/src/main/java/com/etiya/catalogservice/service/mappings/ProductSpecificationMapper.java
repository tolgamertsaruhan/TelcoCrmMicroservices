package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.ProductSpecification;
import com.etiya.catalogservice.service.requests.productSpecification.CreateProductSpecificationRequest;
import com.etiya.catalogservice.service.requests.productSpecification.UpdateProductSpecificationRequest;
import com.etiya.catalogservice.service.responses.productSpecification.CreatedProductSpecificationResponse;
import com.etiya.catalogservice.service.responses.productSpecification.UpdatedProductSpecificationResponse;
import com.etiya.catalogservice.service.responses.productSpecification.GetProductSpecificationResponse;
import com.etiya.catalogservice.service.responses.productSpecification.GetListProductSpecificationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductSpecificationMapper {

    ProductSpecificationMapper INSTANCE = Mappers.getMapper(ProductSpecificationMapper.class);

    void updateProductSpecificationFromRequest(UpdateProductSpecificationRequest request, @MappingTarget ProductSpecification productSpecification);

    ProductSpecification productSpecificationFromCreateProductSpecificationRequest(CreateProductSpecificationRequest request);

    ProductSpecification productSpecificationFromUpdateProductSpecificationRequest(UpdateProductSpecificationRequest request);

    CreatedProductSpecificationResponse createdProductSpecificationResponseFromProductSpecification(ProductSpecification productSpecification);

    UpdatedProductSpecificationResponse UpdatedProductSpecificationResponseFromProductSpecification(ProductSpecification productSpecification);

    GetProductSpecificationResponse GetProductSpecificationResponseProductSpecification(ProductSpecification productSpecification);

    List<GetListProductSpecificationResponse> GetListProductSpecificationResponseFromProductSpecificationList(List<ProductSpecification> productSpecifications);
}

