package com.etiya.customerservice.controller;


import com.etiya.customerservice.domain.entities.City;
import com.etiya.customerservice.service.abstracts.CityService;
import com.etiya.customerservice.service.requests.city.CreateCityRequest;
import com.etiya.customerservice.service.requests.city.UpdateCityRequest;
import com.etiya.customerservice.service.responses.city.CreatedCityResponse;
import com.etiya.customerservice.service.responses.city.GetCityResponse;
import com.etiya.customerservice.service.responses.city.GetListCityResponse;
import com.etiya.customerservice.service.responses.city.UpdatedCityResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCityResponse add(@Valid @RequestBody CreateCityRequest request) {
        return cityService.add(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListCityResponse> getAll() {
        return cityService.getAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetCityResponse getById(@PathVariable("id") UUID id) {
        return cityService.getById(id);
    }

    @GetMapping("getByNameContainsIgnoreCase/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListCityResponse> getByNameContainsIgnoreCase(@PathVariable("name") String pattern) {
        return cityService.getByNameContainsIgnoreCase(pattern);
    }

    @GetMapping("getCitiesWithoutDistrict")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListCityResponse> getCitiesWithoutDistrict() {
        return cityService.getCitiesWithoutDistrict();
    }

    @GetMapping("getCitiesByNameStartingWith/{prefix}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListCityResponse> getCitiesByNameStartingWith(@RequestParam String prefix) {
        return cityService.getByNameStartingWith(prefix);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCityResponse update(@Valid @RequestBody UpdateCityRequest request) {
        return cityService.update(request);
    }
}
