package com.etiya.searchservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(indexName = "customersearch")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSearch {
    @Id
    private String id;
    private String customerNumber;
    private String firstName;
    private String lastName;
    private String nationalId;
    private LocalDateTime dateOfBirth;
    private String motherName;
    private String fatherName;
    private String gender;
}
