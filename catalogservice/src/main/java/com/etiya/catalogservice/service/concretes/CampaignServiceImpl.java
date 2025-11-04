package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.Campaign;
import com.etiya.catalogservice.repository.CampaignRepository;
import com.etiya.catalogservice.service.abstracts.CampaignService;
import com.etiya.catalogservice.service.mappings.CampaignMapper;
import com.etiya.catalogservice.service.requests.campaign.CreateCampaignRequest;
import com.etiya.catalogservice.service.requests.campaign.UpdateCampaignRequest;
import com.etiya.catalogservice.service.responses.campaign.CreatedCampaignResponse;
import com.etiya.catalogservice.service.responses.campaign.GetCampaignResponse;
import com.etiya.catalogservice.service.responses.campaign.GetListCampaignResponse;
import com.etiya.catalogservice.service.responses.campaign.UpdatedCampaignResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;

    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public CreatedCampaignResponse add(CreateCampaignRequest request) {
        Campaign campaign = CampaignMapper.INSTANCE.campaignFromCreateRequest(request);
        Campaign created = campaignRepository.save(campaign);
        return CampaignMapper.INSTANCE.createdResponseFromCampaign(created);
    }

    @Override
    public UpdatedCampaignResponse update(UpdateCampaignRequest request) {
        Campaign existing = campaignRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Campaign not found"));

        Campaign updatedCampaign = CampaignMapper.INSTANCE.campaignFromUpdateRequest(request, existing);
        Campaign updated = campaignRepository.save(updatedCampaign);
        return CampaignMapper.INSTANCE.updatedResponseFromCampaign(updated);
    }

    @Override
    public List<GetListCampaignResponse> getAll() {
        List<Campaign> campaigns = campaignRepository.findAll();
        return CampaignMapper.INSTANCE.getListCampaignResponsesFromCampaigns(campaigns);
    }

    @Override
    public GetCampaignResponse getById(UUID id) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
        return CampaignMapper.INSTANCE.getCampaignResponseFromCampaign(campaign);
    }

    @Override
    public void delete(UUID id) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
        campaignRepository.delete(campaign);
    }

    @Override
    public void softDelete(UUID id) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
        campaign.setDeletedDate(LocalDateTime.now());
        campaignRepository.save(campaign);
    }
}
