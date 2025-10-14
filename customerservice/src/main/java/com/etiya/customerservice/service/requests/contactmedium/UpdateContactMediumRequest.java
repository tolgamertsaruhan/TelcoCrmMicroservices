package com.etiya.customerservice.service.requests.contactmedium;


import com.etiya.customerservice.domain.enums.ContactMediumType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateContactMediumRequest {
    private int id;
    private ContactMediumType type;
    private String value;
    private boolean isPrimary;
    private int customerId;
}
