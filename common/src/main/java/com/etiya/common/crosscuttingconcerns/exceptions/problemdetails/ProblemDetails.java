package com.etiya.common.crosscuttingconcerns.exceptions.problemdetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDetails {
    private String type;
    private String title;
    private int status;
    private String detail;
}
