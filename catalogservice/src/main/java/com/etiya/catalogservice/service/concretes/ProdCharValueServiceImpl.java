package com.etiya.catalogservice.service.concretes;


import com.etiya.catalogservice.domain.entities.ProdCharValue;
import com.etiya.catalogservice.repository.ProdCharValueRepository;
import com.etiya.catalogservice.service.abstracts.ProdCharValueService;
import com.etiya.catalogservice.service.mappings.ProdCharValueMapper;
import com.etiya.catalogservice.service.requests.prodCharValue.CreateProdCharValueRequest;
import com.etiya.catalogservice.service.requests.prodCharValue.UpdateProdCharValueRequest;
import com.etiya.catalogservice.service.responses.prodCharValue.CreatedProdCharValueResponse;
import com.etiya.catalogservice.service.responses.prodCharValue.GetListProdCharValueResponse;
import com.etiya.catalogservice.service.responses.prodCharValue.GetProdCharValueResponse;
import com.etiya.catalogservice.service.responses.prodCharValue.UpdatedProdCharValueResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProdCharValueServiceImpl implements ProdCharValueService {

    private final ProdCharValueRepository prodCharValueRepository;

    public ProdCharValueServiceImpl(ProdCharValueRepository prodCharValueRepository) {
        this.prodCharValueRepository = prodCharValueRepository;
    }

    @Override
    public CreatedProdCharValueResponse add(CreateProdCharValueRequest request) {
        ProdCharValue prodCharValue = ProdCharValueMapper.INSTANCE.prodCharValueFromCreateRequest(request);
        ProdCharValue created = prodCharValueRepository.save(prodCharValue);
        return ProdCharValueMapper.INSTANCE.createdResponseFromProdCharValue(created);
    }

    @Override
    public UpdatedProdCharValueResponse update(UpdateProdCharValueRequest request) {
        ProdCharValue existing = prodCharValueRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("ProdCharValue not found"));

        ProdCharValue updatedProdCharValue = ProdCharValueMapper.INSTANCE.prodCharValueFromUpdateRequest(request, existing);
        ProdCharValue updated = prodCharValueRepository.save(updatedProdCharValue);
        return ProdCharValueMapper.INSTANCE.updatedResponseFromProdCharValue(updated);
    }

    @Override
    public List<GetListProdCharValueResponse> getAll() {
        List<ProdCharValue> prodCharValues = prodCharValueRepository.findAll();
        return ProdCharValueMapper.INSTANCE.getListProdCharValueResponsesFromProdCharValues(prodCharValues);
    }

    @Override
    public GetProdCharValueResponse getById(UUID id) {
        ProdCharValue prodCharValue = prodCharValueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProdCharValue not found"));
        return ProdCharValueMapper.INSTANCE.getProdCharValueResponseFromProdCharValue(prodCharValue);
    }

    @Override
    public void delete(UUID id) {
        ProdCharValue prodCharValue = prodCharValueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProdCharValue not found"));
        prodCharValueRepository.delete(prodCharValue);
    }

    @Override
    public void softDelete(UUID id) {
        ProdCharValue prodCharValue = prodCharValueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProdCharValue not found"));
        prodCharValue.setDeletedDate(LocalDateTime.now());
        prodCharValueRepository.save(prodCharValue);
    }
}