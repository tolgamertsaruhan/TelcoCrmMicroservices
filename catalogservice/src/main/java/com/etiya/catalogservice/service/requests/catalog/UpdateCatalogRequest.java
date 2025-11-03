package com.etiya.catalogservice.service.requests.catalog;

import java.util.UUID;

public class UpdateCatalogRequest {
    private UUID id;
    private String name;
    private UUID parentId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public UpdateCatalogRequest(UUID id, String name, UUID parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public UpdateCatalogRequest() {
    }
}
