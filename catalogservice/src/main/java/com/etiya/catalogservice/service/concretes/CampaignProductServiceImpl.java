package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.CampaignProduct;
import com.etiya.catalogservice.repository.CampaignProductRepository;
import com.etiya.catalogservice.service.abstracts.CampaignProductService;
import com.etiya.catalogservice.service.mappings.CampaignProductMapper;
import com.etiya.catalogservice.service.requests.campaignProduct.CreateCampaignProductRequest;
import com.etiya.catalogservice.service.requests.campaignProduct.UpdateCampaignProductRequest;
import com.etiya.catalogservice.service.responses.campaignProduct.CreatedCampaignProductResponse;
import com.etiya.catalogservice.service.responses.campaignProduct.GetCampaignProductResponse;
import com.etiya.catalogservice.service.responses.campaignProduct.GetListCampaignProductResponse;
import com.etiya.catalogservice.service.responses.campaignProduct.UpdatedCampaignProductResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CampaignProductServiceImpl implements CampaignProductService {

    private final CampaignProductRepository campaignProductRepository;

    public CampaignProductServiceImpl(CampaignProductRepository campaignProductRepository) {
        this.campaignProductRepository = campaignProductRepository;
    }

    @Override
    public CreatedCampaignProductResponse add(CreateCampaignProductRequest request) {
        CampaignProduct campaignProduct = CampaignProductMapper.INSTANCE.campaignProductFromCreateRequest(request);
        CampaignProduct created = campaignProductRepository.save(campaignProduct);
        return CampaignProductMapper.INSTANCE.createdResponseFromCampaignProduct(created);
    }

    @Override
    public UpdatedCampaignProductResponse update(UpdateCampaignProductRequest request) {
        CampaignProduct existing = campaignProductRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("CampaignProduct not found"));

        CampaignProduct updatedEntity = CampaignProductMapper.INSTANCE.campaignProductFromUpdateRequest(request, existing);
        CampaignProduct updated = campaignProductRepository.save(updatedEntity);

        return CampaignProductMapper.INSTANCE.updatedResponseFromCampaignProduct(updated);
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
    public List<GetListCampaignProductResponse> getAll() {
        List<CampaignProduct> campaignProducts = campaignProductRepository.findAll();
        return CampaignProductMapper.INSTANCE.getListResponsesFromEntities(campaignProducts);
    }

    @Override
    public GetCampaignProductResponse getById(UUID id) {
        CampaignProduct campaignProduct = campaignProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CampaignProduct not found"));
        return CampaignProductMapper.INSTANCE.getCampaignProductResponseFromEntity(campaignProduct);
    }

    @Override
    public void delete(UUID id) {
        CampaignProduct campaignProduct = campaignProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CampaignProduct not found"));
        campaignProductRepository.delete(campaignProduct);
    }

    @Override
    public void softDelete(UUID id) {
        CampaignProduct campaignProduct = campaignProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CampaignProduct not found"));
        campaignProduct.setDeletedDate(LocalDateTime.now());
        campaignProductRepository.save(campaignProduct);
    }
}
