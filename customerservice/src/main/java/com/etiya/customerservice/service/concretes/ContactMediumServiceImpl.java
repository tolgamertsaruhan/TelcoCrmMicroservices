package com.etiya.customerservice.service.concretes;


import com.etiya.common.events.CreateAddressEvent;
import com.etiya.common.events.CreateContactMediumEvent;
import com.etiya.common.events.DeleteContactMediumEvent;
import com.etiya.common.events.UpdateContactMediumEvent;
import com.etiya.customerservice.domain.entities.ContactMedium;
import com.etiya.customerservice.repository.ContactMediumRepository;
import com.etiya.customerservice.service.abstracts.CityService;
import com.etiya.customerservice.service.abstracts.ContactMediumService;
import com.etiya.customerservice.service.abstracts.CustomerService;
import com.etiya.customerservice.service.mappings.ContactMediumMapper;
import com.etiya.customerservice.service.requests.contactmedium.CreateContactMediumRequest;
import com.etiya.customerservice.service.requests.contactmedium.UpdateContactMediumRequest;
import com.etiya.customerservice.service.responses.contactmedium.CreatedContactMediumResponse;
import com.etiya.customerservice.service.responses.contactmedium.GetContactMediumResponse;
import com.etiya.customerservice.service.responses.contactmedium.GetListContactMediumResponse;
import com.etiya.customerservice.service.responses.contactmedium.UpdatedContactMediumResponse;
import com.etiya.customerservice.service.rules.ContactMediumBusinessRules;
import com.etiya.customerservice.transport.kafka.producer.contactMedium.CreateContactMediumProducer;
import com.etiya.customerservice.transport.kafka.producer.contactMedium.DeleteContactMediumProducer;
import com.etiya.customerservice.transport.kafka.producer.contactMedium.UpdateContactMediumProducer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ContactMediumServiceImpl implements ContactMediumService {
    private final ContactMediumRepository contactMediumRepository;
    private final ContactMediumBusinessRules contactMediumBusinessRules;
    private final CustomerService customerService;
    private final CreateContactMediumProducer createContactMediumProducer;
    private final UpdateContactMediumProducer updateContactMediumProducer;
    private final DeleteContactMediumProducer deleteContactMediumProducer;

    public ContactMediumServiceImpl(ContactMediumRepository contactMediumRepository, ContactMediumBusinessRules contactMediumBusinessRules, CustomerService customerService, CreateContactMediumProducer createContactMediumProducer, UpdateContactMediumProducer updateContactMediumProducer, DeleteContactMediumProducer deleteContactMediumProducer) {
        this.contactMediumRepository = contactMediumRepository;
        this.contactMediumBusinessRules = contactMediumBusinessRules;
        this.customerService = customerService;
        this.createContactMediumProducer = createContactMediumProducer;
        this.updateContactMediumProducer = updateContactMediumProducer;
        this.deleteContactMediumProducer = deleteContactMediumProducer;
    }


    @Override
    public CreatedContactMediumResponse add(CreateContactMediumRequest request) {
        ContactMedium contactMedium = ContactMediumMapper.INSTANCE.getContactMediumFromCreateContactMediumRequest(request);
        contactMediumBusinessRules.checkIsPrimaryOnlyOne(contactMedium);
        customerService.existsById(request.getCustomerId());
        ContactMedium created =  contactMediumRepository.save(contactMedium);

        CreateContactMediumEvent event =
                new CreateContactMediumEvent(created.getId().toString(),
                        created.getCustomer().getId().toString(),
                        created.getType().toString(),
                        created.getValue(),
                        created.isPrimary());

        createContactMediumProducer.produceContactMediumCreated(event);

        CreatedContactMediumResponse response = ContactMediumMapper.INSTANCE.getCreatedContactMediumResponseFromContactMedium(created);
        response.setId(created.getId());
        return response;
    }

    @Override
    public UpdatedContactMediumResponse update(UpdateContactMediumRequest request) {
        ContactMedium contactMediumOld = contactMediumRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Contact Medium not found"));

        ContactMedium contactMedium = ContactMediumMapper.INSTANCE.contactMediumFromUpdateContactMediumRequest(request, contactMediumOld);
        ContactMedium updated = contactMediumRepository.save(contactMedium);

        UpdateContactMediumEvent event =
                new UpdateContactMediumEvent(updated.getId().toString(),
                        updated.getCustomer().getId().toString(),
                        updated.getType().toString(),
                        updated.getValue(),
                        updated.isPrimary());

        updateContactMediumProducer.produceContactMediumUpdated(event);

        UpdatedContactMediumResponse response = ContactMediumMapper.INSTANCE.getUpdatedContactMediumResponseFromContactMedium(updated);

        return response;
    }


    @Override
    public List<GetListContactMediumResponse> getList() {
        List<ContactMedium> contactMediums = contactMediumRepository.findAll();

        List<GetListContactMediumResponse> responses = ContactMediumMapper.INSTANCE.getListContactMediumResponsesFromContactMedium(contactMediums);

        return responses;
    }

    @Override
    public void delete(UUID id) { //kalıcı silme- hard delete
        ContactMedium contactMedium = contactMediumRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        contactMediumBusinessRules.checkIsPrimary(contactMedium);

        DeleteContactMediumEvent event =
                new DeleteContactMediumEvent(contactMedium.getId().toString(),
                        contactMedium.getCustomer().getId().toString());

        deleteContactMediumProducer.produceContactMediumDeleted(event);

        contactMediumRepository.delete(contactMedium);

    }

    @Override
    public void softDelete(UUID id) {
        ContactMedium contactMedium = contactMediumRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        contactMediumBusinessRules.checkIsPrimary(contactMedium);
        contactMedium.setDeletedDate(LocalDateTime.now());
        contactMediumRepository.save(contactMedium);
    }

    @Override
    public GetContactMediumResponse getById(UUID id) {

        ContactMedium contactMedium = contactMediumRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        GetContactMediumResponse response = ContactMediumMapper.INSTANCE.getContactMediumResponseFromContactMedium(contactMedium);
        return response;
    }

    @Override
    public GetContactMediumResponse getByValue(String value) {
        ContactMedium contactMedium =
                contactMediumRepository.findByValue(value);
        GetContactMediumResponse response =
                ContactMediumMapper.INSTANCE.getContactMediumResponseFromContactMedium(contactMedium);
        return response;
    }

    @Override
    public List<GetListContactMediumResponse> getListByType(String type) {
        List<ContactMedium> contactMediums = contactMediumRepository.findByType(type);
        List<GetListContactMediumResponse> responses =
                ContactMediumMapper.INSTANCE.getListContactMediumResponsesFromContactMedium(contactMediums);
        return responses;
    }

    @Override
    public List<GetListContactMediumResponse> getListByCustomerId(UUID id) {
        List<ContactMedium> contactMediums = contactMediumRepository.findByCustomerId(id);
        List<GetListContactMediumResponse> responses = ContactMediumMapper.INSTANCE.getListContactMediumResponsesFromContactMedium(contactMediums);
        return responses;
    }


}
