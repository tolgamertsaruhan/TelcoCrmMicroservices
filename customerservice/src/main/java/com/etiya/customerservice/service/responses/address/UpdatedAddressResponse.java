package com.etiya.customerservice.service.responses.address;



import java.util.UUID;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class UpdatedAddressResponse {
    private UUID id;
    private String street;
    private String houseNumber;
    private String description;
    private boolean isDefault;
    private UUID districtId;
    private UUID customerId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public UUID getDistrictId() {
        return districtId;
    }

    public void setDistrictId(UUID districtId) {
        this.districtId = districtId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UpdatedAddressResponse() {
    }

    public UpdatedAddressResponse(UUID id, String street, String houseNumber, String description, boolean isDefault, UUID districtId, UUID customerId) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.description = description;
        this.isDefault = isDefault;
        this.districtId = districtId;
        this.customerId = customerId;
    }
}
