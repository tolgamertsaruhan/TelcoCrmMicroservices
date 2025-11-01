package com.etiya.searchservice.domain;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class ContactMedium {
    private String contactMediumId;
    private String customerId;
    //@Field(type = FieldType.Keyword)
    private String type;

    //@Field(type = FieldType.Keyword)
    private String value;
    private boolean isPrimary;

    private String deletedDate;

    public ContactMedium() {
    }

    public String getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(String deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getContactMediumId() {
        return contactMediumId;
    }

    public void setContactMediumId(String contactMediumId) {
        this.contactMediumId = contactMediumId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public void setPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public ContactMedium(String contactMediumId, String customerId, String type, String value, boolean isPrimary) {
        this.contactMediumId = contactMediumId;
        this.customerId = customerId;
        this.type = type;
        this.value = value;
        this.isPrimary = isPrimary;
    }

    public ContactMedium(String contactMediumId, String customerId) {
        this.contactMediumId = contactMediumId;
        this.customerId = customerId;
    }

    public ContactMedium(String contactMediumId, String customerId, String type, String value, boolean isPrimary, String deletedDate) {
        this.contactMediumId = contactMediumId;
        this.customerId = customerId;
        this.type = type;
        this.value = value;
        this.isPrimary = isPrimary;
        this.deletedDate = deletedDate;
    }
}
