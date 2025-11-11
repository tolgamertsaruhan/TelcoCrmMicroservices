package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.Characteristic;
import com.etiya.catalogservice.repository.CharacteristicRepository;
import com.etiya.catalogservice.service.abstracts.CharacteristicService;
import com.etiya.catalogservice.service.mappings.CharacteristicMapper;
import com.etiya.catalogservice.service.requests.characteristic.CreateCharacteristicRequest;
import com.etiya.catalogservice.service.requests.characteristic.UpdateCharacteristicRequest;
import com.etiya.catalogservice.service.responses.characteristic.CreatedCharacteristicResponse;
import com.etiya.catalogservice.service.responses.characteristic.GetCharacteristicResponse;
import com.etiya.catalogservice.service.responses.characteristic.GetListCharacteristicResponse;
import com.etiya.catalogservice.service.responses.characteristic.UpdatedCharacteristicResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CharacteristicServiceImpl implements CharacteristicService {

    private final CharacteristicRepository characteristicRepository;

    public CharacteristicServiceImpl(CharacteristicRepository characteristicRepository) {
        this.characteristicRepository = characteristicRepository;
    }

    @Override
    public CreatedCharacteristicResponse add(CreateCharacteristicRequest request) {
        Characteristic characteristic = CharacteristicMapper.INSTANCE.characteristicFromCreateRequest(request);
        Characteristic created = characteristicRepository.save(characteristic);
        return CharacteristicMapper.INSTANCE.createdResponseFromCharacteristic(created);
    }

    @Override
    public UpdatedCharacteristicResponse update(UpdateCharacteristicRequest request) {
        Characteristic existing = characteristicRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Characteristic not found"));

        Characteristic updatedCharacteristic = CharacteristicMapper.INSTANCE.characteristicFromUpdateRequest(request, existing);
        Characteristic updated = characteristicRepository.save(updatedCharacteristic);
        return CharacteristicMapper.INSTANCE.updatedResponseFromCharacteristic(updated);
    }

    @Override
    public List<GetListCharacteristicResponse> getAll() {
        List<Characteristic> characteristics = characteristicRepository.findAll();
        return CharacteristicMapper.INSTANCE.getListCharacteristicResponsesFromCharacteristics(characteristics);
    }

    @Override
    public GetCharacteristicResponse getById(UUID id) {
        Characteristic characteristic = characteristicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Characteristic not found"));
        return CharacteristicMapper.INSTANCE.getCharacteristicResponseFromCharacteristic(characteristic);
    }

    @Override
    public void delete(UUID id) {
        Characteristic characteristic = characteristicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Characteristic not found"));
        characteristicRepository.delete(characteristic);
    }

    @Override
    public void softDelete(UUID id) {
        Characteristic characteristic = characteristicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Characteristic not found"));
        characteristic.setDeletedDate(LocalDateTime.now());
        characteristicRepository.save(characteristic);
    }
}