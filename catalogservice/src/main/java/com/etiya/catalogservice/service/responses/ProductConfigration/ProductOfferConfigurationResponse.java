package com.etiya.catalogservice.service.responses.ProductConfigration;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ProductOfferConfigurationResponse {
    private UUID productOfferId;
    private String productOfferName;
    private BigDecimal price;
    private List<ProductCharacteristicResponse> characteristics;

    public UUID getProductOfferId() {
        return productOfferId;
    }

    public void setProductOfferId(UUID productOfferId) {
        this.productOfferId = productOfferId;
    }

    public String getProductOfferName() {
        return productOfferName;
    }

    public void setProductOfferName(String productOfferName) {
        this.productOfferName = productOfferName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<ProductCharacteristicResponse> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<ProductCharacteristicResponse> characteristics) {
        this.characteristics = characteristics;
    }

    public ProductOfferConfigurationResponse() {
    }

    public ProductOfferConfigurationResponse(UUID productOfferId, String productOfferName, BigDecimal price, List<ProductCharacteristicResponse> characteristics) {
        this.productOfferId = productOfferId;
        this.productOfferName = productOfferName;
        this.price = price;
        this.characteristics = characteristics;
    }
}
