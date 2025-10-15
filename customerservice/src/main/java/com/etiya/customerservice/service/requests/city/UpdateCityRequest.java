package com.etiya.customerservice.service.requests.city;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class UpdateCityRequest {

    private UUID id;
    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[a-zA-ZçÇşŞğĞıİüÜöÖ]+", message = "Name must contain only letters.")
    private String name;

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

    public UpdateCityRequest() {
    }

    public UpdateCityRequest(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
