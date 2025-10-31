package com.etiya.customerservice.controller;


import com.etiya.customerservice.service.abstracts.ContactMediumService;
import com.etiya.customerservice.service.requests.contactmedium.CreateContactMediumRequest;
import com.etiya.customerservice.service.requests.contactmedium.UpdateContactMediumRequest;
import com.etiya.customerservice.service.responses.contactmedium.CreatedContactMediumResponse;
import com.etiya.customerservice.service.responses.contactmedium.GetContactMediumResponse;
import com.etiya.customerservice.service.responses.contactmedium.GetListContactMediumResponse;
import com.etiya.customerservice.service.responses.contactmedium.UpdatedContactMediumResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contactmediums")
public class ContactMediumController {
    private final ContactMediumService contactMediumService;

    public ContactMediumController(ContactMediumService contactMediumService) {
        this.contactMediumService = contactMediumService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedContactMediumResponse createContactMedium(@Valid @RequestBody CreateContactMediumRequest request) {
        return contactMediumService.add(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedContactMediumResponse updateContactMedium(@RequestBody UpdateContactMediumRequest request) {
        return contactMediumService.update(request);
    }

    @DeleteMapping("{id}")//pathvariable ile anlaşsın diye, mapping yapsın diye
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        contactMediumService.delete(id);
    }

    @DeleteMapping("{id}/soft")
    @ResponseStatus(HttpStatus.OK)
    public void softDelete(@PathVariable String id){
        contactMediumService.softDelete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListContactMediumResponse> getList(){
        return contactMediumService.getList();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetContactMediumResponse getById(@PathVariable UUID id) {
        return contactMediumService.getById(id);
    }

    @GetMapping("{value}/value") // <-- BU KURAL KESİNLİKLE KALMALI
    @ResponseStatus(HttpStatus.OK)
    public GetContactMediumResponse getByValueStartsWith(@PathVariable String value) {
        return contactMediumService.getByValue(value);
    }

    @GetMapping("{type}/type") // <-- BU KURAL KESİNLİKLE KALMALI
    @ResponseStatus(HttpStatus.OK)
    public List<GetListContactMediumResponse> getListByType(@PathVariable String type) {
        return contactMediumService.getListByType(type);
    }
    @GetMapping("{customerId}/customerId") // <-- BU KURAL KESİNLİKLE KALMALI
    @ResponseStatus(HttpStatus.OK)
    public List<GetListContactMediumResponse> getListByCustomerId(@PathVariable UUID customerId) {
        return contactMediumService.getListByCustomerId(customerId);
    }

    @GetMapping("/contacts-for-customer/{customerId}")
    public List<GetContactMediumResponse> getByCustomerId(@PathVariable String customerId) {
        return contactMediumService.getByCustomerId(customerId);
    }

}
