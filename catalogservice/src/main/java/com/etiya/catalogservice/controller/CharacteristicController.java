package com.etiya.catalogservice.controller;
import com.etiya.catalogservice.service.abstracts.CharacteristicService;
import com.etiya.catalogservice.service.requests.characteristic.CreateCharacteristicRequest;
import com.etiya.catalogservice.service.requests.characteristic.UpdateCharacteristicRequest;
import com.etiya.catalogservice.service.responses.characteristic.CreatedCharacteristicResponse;
import com.etiya.catalogservice.service.responses.characteristic.GetCharacteristicResponse;
import com.etiya.catalogservice.service.responses.characteristic.GetListCharacteristicResponse;
import com.etiya.catalogservice.service.responses.characteristic.UpdatedCharacteristicResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/characteristics")
public class CharacteristicController {
    private final CharacteristicService characteristicService;

    public CharacteristicController(CharacteristicService characteristicService) {
        this.characteristicService = characteristicService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCharacteristicResponse add(@RequestBody CreateCharacteristicRequest request) {
        return characteristicService.add(request);
    }

    @PutMapping
    public UpdatedCharacteristicResponse update(@RequestBody UpdateCharacteristicRequest request) {
        return characteristicService.update(request);
    }

    @GetMapping
    public List<GetListCharacteristicResponse> getAll() {
        return characteristicService.getAll();
    }

    @GetMapping("/{id}")
    public GetCharacteristicResponse getById(@PathVariable UUID id) {
        return characteristicService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        characteristicService.delete(id);
    }

    @DeleteMapping("/{id}/soft-delete")
    public void softDelete(@PathVariable UUID id) {
        characteristicService.softDelete(id);
    }
}
