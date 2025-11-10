package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.CampaignProductOffer;
import com.etiya.catalogservice.repository.CampaignProductOfferRepository;
import com.etiya.catalogservice.service.abstracts.CampaignProductOfferService;
import com.etiya.catalogservice.service.mappings.CampaignProductOfferMapper;
import com.etiya.catalogservice.service.requests.campaignProductOffer.CreateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.requests.campaignProductOffer.UpdateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.responses.campaignProductOffer.CreatedCampaignProductOfferResponse;
import com.etiya.catalogservice.service.responses.campaignProductOffer.GetCampaignProductOfferResponse;
import com.etiya.catalogservice.service.responses.campaignProductOffer.GetListCampaignProductOfferResponse;
import com.etiya.catalogservice.service.responses.campaignProductOffer.UpdatedCampaignProductOfferResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CampaignProductOfferServiceImpl implements CampaignProductOfferService {

    private final CampaignProductOfferRepository campaignProductOfferRepository;

    public CampaignProductOfferServiceImpl(CampaignProductOfferRepository campaignProductOfferRepository) {
        this.campaignProductOfferRepository = campaignProductOfferRepository;
    }

    @Override
    public CreatedCampaignProductOfferResponse add(CreateCampaignProductOfferRequest request) {
        CampaignProductOffer campaignProductOffer = CampaignProductOfferMapper.INSTANCE.campaignProductFromCreateRequest(request);
        CampaignProductOffer created = campaignProductOfferRepository.save(campaignProductOffer);
        return CampaignProductOfferMapper.INSTANCE.createdResponseFromCampaignProduct(created);
    }

    @Override
    public UpdatedCampaignProductOfferResponse update(UpdateCampaignProductOfferRequest request) {
        CampaignProductOffer existing = campaignProductOfferRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("CampaignProduct not found"));

        CampaignProductOffer updatedEntity = CampaignProductOfferMapper.INSTANCE.campaignProductFromUpdateRequest(request, existing);
        CampaignProductOffer updated = campaignProductOfferRepository.save(updatedEntity);

        return CampaignProductOfferMapper.INSTANCE.updatedResponseFromCampaignProduct(updated);
    }

    /*@Override
    public UpdatedCampaignProductResponse update(UpdateCampaignProductRequest request) {
        CampaignProduct existing = campaignProductRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("CampaignProduct not found"));

        // Campaign ve Product DB'den çekiliyor
        Campaign campaign = campaignRepository.findById(request.getCampaignId())
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Mapper sadece primitive alanları update ediyor
        CampaignProductMapper.INSTANCE.campaignProductFromUpdateRequest(request, existing);

        // İlişkileri elle güncelliyoruz
        existing.setCampaign(campaign);
        existing.setProduct(product);

        CampaignProduct updated = campaignProductRepository.save(existing);

        return CampaignProductMapper.INSTANCE.updatedResponseFromCampaignProduct(updated);
    }*/

    @Override
    public List<GetListCampaignProductOfferResponse> getAll() {
        List<CampaignProductOffer> campaignProductOffers = campaignProductOfferRepository.findAll();
        return CampaignProductOfferMapper.INSTANCE.getListResponsesFromEntities(campaignProductOffers);
    }

    @Override
    public GetCampaignProductOfferResponse getById(UUID id) {
        CampaignProductOffer campaignProductOffer = campaignProductOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CampaignProduct not found"));
        return CampaignProductOfferMapper.INSTANCE.getCampaignProductResponseFromEntity(campaignProductOffer);
    }

    @Override
    public void delete(UUID id) {
        CampaignProductOffer campaignProductOffer = campaignProductOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CampaignProduct not found"));
        campaignProductOfferRepository.delete(campaignProductOffer);
    }

    @Override
    public void softDelete(UUID id) {
        CampaignProductOffer campaignProductOffer = campaignProductOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CampaignProduct not found"));
        campaignProductOffer.setDeletedDate(LocalDateTime.now());
        campaignProductOfferRepository.save(campaignProductOffer);
    }

    @Override
    public GetCampaignProductOfferResponse findCampaignsByProductOfferId(UUID productOfferId) {
        CampaignProductOffer campaignProductsOffer = campaignProductOfferRepository.findByProductOffer_Id(productOfferId);
        return CampaignProductOfferMapper.INSTANCE.getCampaignProductResponseFromEntity(campaignProductsOffer);
    }

    @Override
    public GetCampaignProductOfferResponse findCampaignProductOfferById(UUID campaignProductOfferId){
        CampaignProductOffer campaignProductOffer = campaignProductOfferRepository.findById(campaignProductOfferId).orElseThrow(() -> new RuntimeException("CampaignProduct not found"));
        return CampaignProductOfferMapper.INSTANCE.getCampaignProductResponseFromEntity(campaignProductOffer);
    }
}
