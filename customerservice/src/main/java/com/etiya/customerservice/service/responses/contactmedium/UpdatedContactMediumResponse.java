package com.etiya.customerservice.service.responses.contactmedium;

import com.etiya.customerservice.domain.enums.ContactMediumType;


import java.util.UUID;


//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class UpdatedContactMediumResponse {
    private UUID id;
    private ContactMediumType type;
    private String value;
    private boolean isPrimary;
    private UUID customerId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ContactMediumType getType() {
        return type;
    }

    public void setType(ContactMediumType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UpdatedContactMediumResponse() {
    }

    public UpdatedContactMediumResponse(UUID id, ContactMediumType type, String value, boolean isPrimary, UUID customerId) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.isPrimary = isPrimary;
        this.customerId = customerId;
    }
}
