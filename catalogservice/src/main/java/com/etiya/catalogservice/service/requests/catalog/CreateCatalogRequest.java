package com.etiya.catalogservice.service.requests.catalog;

import java.util.UUID;

public class CreateCatalogRequest {
    private String name;
    private UUID parentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getParentId() {
        return parentId;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }

    public CreateCatalogRequest(String name, UUID parentId) {
        this.name = name;
        this.parentId = parentId;
    }

    public CreateCatalogRequest() {
    }
}
