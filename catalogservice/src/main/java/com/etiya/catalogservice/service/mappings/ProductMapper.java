package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.Product;
import com.etiya.catalogservice.service.requests.product.CreateProductRequest;
import com.etiya.catalogservice.service.requests.product.UpdateProductRequest;
import com.etiya.catalogservice.service.responses.product.CreatedProductResponse;
import com.etiya.catalogservice.service.responses.product.UpdatedProductResponse;
import com.etiya.catalogservice.service.responses.product.GetProductResponse;
import com.etiya.catalogservice.service.responses.product.GetListProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "catalogId", target = "catalog.id")
    @Mapping(source = "specificationId", target = "productSpecification.id")
    @Mapping(source = "productOfferId", target = "productOffer.id")
    Product productFromCreateProductRequest(CreateProductRequest request);

    @Mapping(source = "catalogId", target = "catalog.id")
    @Mapping(source = "specificationId", target = "productSpecification.id")
    @Mapping(source = "productOfferId", target = "productOffer.id")
    Product productFromUpdateProductRequest(UpdateProductRequest request);

    void updateProductFromRequest(UpdateProductRequest request, @MappingTarget Product product);

    @Mapping(source = "catalog.id", target = "catalogId")
    @Mapping(source = "productSpecification.id", target = "specificationId")
    @Mapping(source = "productOffer.id", target = "productOfferId")
    CreatedProductResponse createdProductResponseFromProduct(Product product);

    @Mapping(source = "catalog.id", target = "catalogId")
    @Mapping(source = "productSpecification.id", target = "specificationId")
    @Mapping(source = "productOffer.id", target = "productOfferId")
    UpdatedProductResponse UpdatedProductResponseFromProduct(Product product);

    @Mapping(source = "catalog.id", target = "catalogId")
    @Mapping(source = "productSpecification.id", target = "specificationId")
    @Mapping(source = "productOffer.id", target = "productOfferId")
    GetProductResponse GetProductResponseProduct(Product product);

    @Mapping(source = "catalog.id", target = "catalogId")
    @Mapping(source = "productSpecification.id", target = "specificationId")
    @Mapping(source = "productOffer.id", target = "productOfferId")
    List<GetListProductResponse> GetListProductResponseFromProductList(List<Product> products);
}

