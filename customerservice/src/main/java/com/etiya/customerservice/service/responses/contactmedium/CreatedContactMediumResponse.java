package com.etiya.customerservice.service.responses.contactmedium;

import com.etiya.customerservice.domain.enums.ContactMediumType;


import java.util.UUID;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class CreatedContactMediumResponse {
    private UUID id;
    private UUID customerId;
    private ContactMediumType type;
    private String value;
    private boolean isPrimary;

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

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

    public CreatedContactMediumResponse() {
    }

    public CreatedContactMediumResponse(UUID id, UUID customerId, ContactMediumType type, String value, boolean isPrimary) {
        this.id = id;
        this.customerId = customerId;
        this.type = type;
        this.value = value;
        this.isPrimary = isPrimary;
    }
}
