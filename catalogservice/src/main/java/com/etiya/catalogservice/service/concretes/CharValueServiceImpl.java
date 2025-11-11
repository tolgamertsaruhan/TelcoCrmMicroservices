package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.CharValue;
import com.etiya.catalogservice.repository.CharValueRepository;
import com.etiya.catalogservice.service.abstracts.CharValueService;
import com.etiya.catalogservice.service.mappings.CharValueMapper;
import com.etiya.catalogservice.service.requests.charValue.CreateCharValueRequest;
import com.etiya.catalogservice.service.requests.charValue.UpdateCharValueRequest;
import com.etiya.catalogservice.service.responses.charValue.CreatedCharValueResponse;
import com.etiya.catalogservice.service.responses.charValue.GetCharValueResponse;
import com.etiya.catalogservice.service.responses.charValue.GetListCharValueResponse;
import com.etiya.catalogservice.service.responses.charValue.UpdatedCharValueResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CharValueServiceImpl  implements CharValueService {

    private final CharValueRepository charValueRepository;

    public CharValueServiceImpl(CharValueRepository charValueRepository) {
        this.charValueRepository = charValueRepository;
    }

    @Override
    public CreatedCharValueResponse add(CreateCharValueRequest request) {
        CharValue charValue = CharValueMapper.INSTANCE.charValueFromCreateRequest(request);
        CharValue created = charValueRepository.save(charValue);
        return CharValueMapper.INSTANCE.createdResponseFromCharValue(created);
    }

    @Override
    public UpdatedCharValueResponse update(UpdateCharValueRequest request) {
        CharValue existing = charValueRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("CharValue not found"));

        CharValue updatedCharValue = CharValueMapper.INSTANCE.charValueFromUpdateRequest(request, existing);
        CharValue updated = charValueRepository.save(updatedCharValue);
        return CharValueMapper.INSTANCE.updatedResponseFromCharValue(updated);
    }

    @Override
    public List<GetListCharValueResponse> getAll() {
        List<CharValue> charValues = charValueRepository.findAll();
        return CharValueMapper.INSTANCE.getListCharValueResponsesFromCharValues(charValues);
    }

    @Override
    public GetCharValueResponse getById(UUID id) {
        CharValue charValue = charValueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CharValue not found"));
        return CharValueMapper.INSTANCE.getCharValueResponseFromCharValue(charValue);
    }

    @Override
    public void delete(UUID id) {
        CharValue charValue = charValueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CharValue not found"));
        charValueRepository.delete(charValue);
    }

    @Override
    public void softDelete(UUID id) {
        CharValue charValue = charValueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CharValue not found"));
        charValue.setDeletedDate(LocalDateTime.now());
        charValueRepository.save(charValue);
    }
}
