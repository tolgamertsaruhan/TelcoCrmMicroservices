package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.requests.catalogProductOffer.CreateCatalogProductOfferRequest;
import com.etiya.catalogservice.service.requests.catalogProductOffer.UpdateCatalogProductOfferRequest;
import com.etiya.catalogservice.service.responses.catalogProductOffer.CreatedCatalogProductOfferResponse;
import com.etiya.catalogservice.service.responses.catalogProductOffer.GetCatalogProductOfferResponse;
import com.etiya.catalogservice.service.responses.catalogProductOffer.GetListCatalogProductOfferResponse;
import com.etiya.catalogservice.service.responses.catalogProductOffer.UpdatedCatalogProductOfferResponse;

import java.util.List;
import java.util.UUID;

public interface CatalogProductOfferService {

    CreatedCatalogProductOfferResponse add(CreateCatalogProductOfferRequest request);
    UpdatedCatalogProductOfferResponse update(UpdateCatalogProductOfferRequest request);
    void delete(UUID id);
    GetCatalogProductOfferResponse getById(UUID id);
    List<GetListCatalogProductOfferResponse> getAll();
    void softDelete(UUID id);
    List<GetListCatalogProductOfferResponse> getCatalogProductOfferByCatalogId(UUID id);
    List<GetListCatalogProductOfferResponse> searchByCatalogIdAndProductOfferName(UUID catalogId, String productOfferName);
}
