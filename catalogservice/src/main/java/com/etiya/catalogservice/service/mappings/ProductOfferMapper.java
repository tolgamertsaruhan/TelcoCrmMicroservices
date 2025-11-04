package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.ProductOffer;
import com.etiya.catalogservice.service.requests.productOffer.CreateProductOfferRequest;
import com.etiya.catalogservice.service.requests.productOffer.UpdateProductOfferRequest;
import com.etiya.catalogservice.service.responses.productOffer.CreatedProductOfferResponse;
import com.etiya.catalogservice.service.responses.productOffer.GetListProductOfferResponse;
import com.etiya.catalogservice.service.responses.productOffer.GetProductOfferResponse;
import com.etiya.catalogservice.service.responses.productOffer.UpdatedProductOfferResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductOfferMapper {

    ProductOfferMapper INSTANCE = Mappers.getMapper(ProductOfferMapper.class);

    @Mapping(target = "discountRate", source = "discountRate")
    ProductOffer productOfferFromCreateProductOfferRequest(CreateProductOfferRequest request);

    void updateProductOfferFromRequest(UpdateProductOfferRequest request, @MappingTarget ProductOffer productOffer);

    @Mapping(target = "discountRate", source = "discountRate")
    CreatedProductOfferResponse createdProductOfferResponseFromProductOffer(ProductOffer productOffer);

    @Mapping(target = "productId", source = "product.id")
    UpdatedProductOfferResponse updatedProductOfferResponseFromProductOffer(ProductOffer productOffer);

    @Mapping(target = "productId", source = "product.id")
    GetProductOfferResponse getProductOfferResponseFromProductOffer(ProductOffer productOffer);

    @Mapping(target = "productId", source = "product.id")
    List<GetListProductOfferResponse> getListProductOfferResponseFromProductOfferList(List<ProductOffer> productOffers);

    @Mapping(target = "id", source = "id")  // Explicit mapping for id
    ProductOffer toProductOffer(UpdateProductOfferRequest updateProductOfferRequest);

}
