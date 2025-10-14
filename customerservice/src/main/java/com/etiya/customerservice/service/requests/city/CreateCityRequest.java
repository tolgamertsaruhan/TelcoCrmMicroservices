package com.etiya.customerservice.service.requests.city;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCityRequest {
    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[a-zA-ZçÇşŞğĞıİüÜöÖ]+", message = "Name must contain only letters.")
    private String name;
}
