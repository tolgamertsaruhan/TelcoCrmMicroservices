package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.concretes.CharValueServiceImpl;
import com.etiya.catalogservice.service.requests.charValue.CreateCharValueRequest;
import com.etiya.catalogservice.service.requests.charValue.UpdateCharValueRequest;
import com.etiya.catalogservice.service.responses.charValue.CreatedCharValueResponse;
import com.etiya.catalogservice.service.responses.charValue.GetCharValueResponse;
import com.etiya.catalogservice.service.responses.charValue.GetListCharValueResponse;
import com.etiya.catalogservice.service.responses.charValue.UpdatedCharValueResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/char-values")
public class CharValueController {

    private final CharValueServiceImpl charValueService;

    public CharValueController(CharValueServiceImpl charValueServiceImpl) {
        this.charValueService = charValueServiceImpl;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCharValueResponse add(@RequestBody CreateCharValueRequest request) {
        return charValueService.add(request);
    }

    @PutMapping
    public UpdatedCharValueResponse update(@RequestBody UpdateCharValueRequest request) {
        return charValueService.update(request);
    }

    @GetMapping
    public List<GetListCharValueResponse> getAll() {
        return charValueService.getAll();
    }

    @GetMapping("/{id}")
    public GetCharValueResponse getById(@PathVariable UUID id) {
        return charValueService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        charValueService.delete(id);
    }

    @DeleteMapping("/{id}/soft-delete")
    public void softDelete(@PathVariable UUID id) {
        charValueService.softDelete(id);
    }
}
