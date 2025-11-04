package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.requests.productOffer.CreateProductOfferRequest;
import com.etiya.catalogservice.service.requests.productOffer.UpdateProductOfferRequest;
import com.etiya.catalogservice.service.responses.productOffer.CreatedProductOfferResponse;
import com.etiya.catalogservice.service.responses.productOffer.GetListProductOfferResponse;
import com.etiya.catalogservice.service.responses.productOffer.GetProductOfferResponse;
import com.etiya.catalogservice.service.responses.productOffer.UpdatedProductOfferResponse;

import java.util.List;
import java.util.UUID;

public interface ProductOfferService {

    CreatedProductOfferResponse addProductOffer(CreateProductOfferRequest request);

    UpdatedProductOfferResponse update(UpdateProductOfferRequest updateProductOfferRequest);

    GetProductOfferResponse getProductOffer(UUID id);

    List<GetListProductOfferResponse> getAllProductOffers();

    void softDelete(UUID id);

    void delete(UUID id);
}
