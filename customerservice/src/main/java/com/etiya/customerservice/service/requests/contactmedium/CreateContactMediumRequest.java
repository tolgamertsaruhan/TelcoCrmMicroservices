package com.etiya.customerservice.service.requests.contactmedium;

import com.etiya.common.validators.annotations.ContactFormat;
import com.etiya.common.validators.annotations.EnumValidator;
import com.etiya.customerservice.domain.enums.ContactMediumType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ContactFormat( // 🎯 İŞTE YENİ ANOTASYONU BURADA KULLANIYORUZ
        typeField = "type",
        valueField = "value",
        message = "Girilen değer, belirtilen iletişim tipiyle uyumlu formatta değil."
)
public class CreateContactMediumRequest {
    @NotBlank(message = "Type couldn't be empty")
    @EnumValidator(
            enumClass = ContactMediumType.class,
            message = "Geçersiz iletişim tipi. Lütfen şunlardan birini kullanın: EMAIL, PHONE, FAX, MOBILE"
    )
    private String type;

    @NotBlank
    @Size(max = 150, message = "Value can't be longer than 150 characters")
    private String value;
    @NotNull
    private boolean isPrimary;
    @NotNull
    @Positive(message = "CityId must be positive")
    private Integer customerId;
}
