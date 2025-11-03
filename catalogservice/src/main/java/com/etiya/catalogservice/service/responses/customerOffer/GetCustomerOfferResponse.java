package com.etiya.catalogservice.service.responses.customerOffer;

import com.etiya.catalogservice.domain.enums.CustomerOfferStatuses;

import java.time.LocalDateTime;
import java.util.UUID;

public class GetCustomerOfferResponse {
    private UUID id;
    private UUID productOfferId; // İlişkili ProductOffer Entity'si (Varsayımsal)
    private LocalDateTime expirationDate; // date -> LocalDate
    private CustomerOfferStatuses status;
//    private UUID customerId;
//
//    public UUID getCustomerId() {
//        return customerId;
//    }
//
//    public void setCustomerId(UUID customerId) {
//        this.customerId = customerId;
//    }

    public CustomerOfferStatuses getStatus() {
        return status;
    }

    public void setStatus(CustomerOfferStatuses status) {
        this.status = status;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public UUID getProductOfferId() {
        return productOfferId;
    }

    public void setProductOfferId(UUID productOfferId) {
        this.productOfferId = productOfferId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
/*
    public CreatedCustomerOfferResponse(UUID id, UUID productOfferId, LocalDateTime expirationDate, CustomerOfferStatuses status, UUID customerId) {
        this.id = id;
        this.productOfferId = productOfferId;
        this.expirationDate = expirationDate;
        this.status = status;
        this.customerId = customerId;
    }*/

    public GetCustomerOfferResponse(UUID id, UUID productOfferId, LocalDateTime expirationDate, CustomerOfferStatuses status) {
        this.id = id;
        this.productOfferId = productOfferId;
        this.expirationDate = expirationDate;
        this.status = status;
    }

    public GetCustomerOfferResponse() {
    }
}
