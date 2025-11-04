package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.CatalogProductOffer;
import com.etiya.catalogservice.repository.CatalogProductOfferRepository;
import com.etiya.catalogservice.service.abstracts.CatalogProductOfferService;
import com.etiya.catalogservice.service.mappings.CatalogProductOfferMapper;
import com.etiya.catalogservice.service.requests.catalogProductOffer.CreateCatalogProductOfferRequest;
import com.etiya.catalogservice.service.requests.catalogProductOffer.UpdateCatalogProductOfferRequest;
import com.etiya.catalogservice.service.responses.catalogProductOffer.CreatedCatalogProductOfferResponse;
import com.etiya.catalogservice.service.responses.catalogProductOffer.GetCatalogProductOfferResponse;
import com.etiya.catalogservice.service.responses.catalogProductOffer.GetListCatalogProductOfferResponse;
import com.etiya.catalogservice.service.responses.catalogProductOffer.UpdatedCatalogProductOfferResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CatalogProductOfferServiceImpl implements CatalogProductOfferService {

    private final CatalogProductOfferRepository catalogProductOfferRepository;

    public CatalogProductOfferServiceImpl(CatalogProductOfferRepository catalogProductOfferRepository) {
        this.catalogProductOfferRepository = catalogProductOfferRepository;
    }

    @Override
    public CreatedCatalogProductOfferResponse add(CreateCatalogProductOfferRequest request) {
        CatalogProductOffer catalogProductOffer =
                CatalogProductOfferMapper.mapper.createCatalogProductOfferRequestToCatalogProductOffer(request);

        CatalogProductOffer created = catalogProductOfferRepository.save(catalogProductOffer);
        return CatalogProductOfferMapper.mapper.catalogProductOfferToCreatedCatalogProductOfferResponse(created);
    }

    @Override
    public UpdatedCatalogProductOfferResponse update(UpdateCatalogProductOfferRequest request) {
        CatalogProductOffer existing = catalogProductOfferRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("CatalogProductOffer not found"));

        CatalogProductOffer updatedEntity =
                CatalogProductOfferMapper.mapper.updateCatalogProductOfferRequestToCatalogProductOffer(request);

        updatedEntity.setCreatedDate(existing.getCreatedDate());
        updatedEntity.setUpdatedDate(LocalDateTime.now());

        CatalogProductOffer updated = catalogProductOfferRepository.save(updatedEntity);
        return CatalogProductOfferMapper.mapper.catalogProductOfferToUpdatedCatalogProductOfferResponse(updated);
    }

    @Override
    public List<GetListCatalogProductOfferResponse> getAll() {
        List<CatalogProductOffer> offers = catalogProductOfferRepository.findAll();
        return CatalogProductOfferMapper.mapper.catalogProductOfferListToGetListCatalogProductOfferResponseList(offers);
    }

    @Override
    public GetCatalogProductOfferResponse getById(UUID id) {
        CatalogProductOffer offer = catalogProductOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatalogProductOffer not found"));
        return CatalogProductOfferMapper.mapper.catalogProductOfferToGetCatalogProductOfferResponse(offer);
    }

    @Override
    public void delete(UUID id) {
        CatalogProductOffer offer = catalogProductOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatalogProductOffer not found"));
        catalogProductOfferRepository.delete(offer);
    }

    @Override
    public void softDelete(UUID id) {
        CatalogProductOffer offer = catalogProductOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatalogProductOffer not found"));
        offer.setDeletedDate(LocalDateTime.now());
        catalogProductOfferRepository.save(offer);
    }
}