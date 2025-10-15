package com.etiya.customerservice.service.requests.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAddressRequest {
    private UUID id;
    @NotBlank(message = "Street is required")
    private String street;
    @NotBlank(message = "House number is required")
    private String houseNumber;
    @Size(max = 255, message = "Description can't be longer than 255 characters")
    private String description;
    private Boolean isDefault;
    private UUID districtId;
}
