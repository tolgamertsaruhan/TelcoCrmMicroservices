package com.etiya.common.crosscuttingconcerns.exceptions.problemdetails;

import com.etiya.common.crosscuttingconcerns.exceptions.constants.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class AuthProblemDetails extends ProblemDetails{
    public AuthProblemDetails() {
        setTitle(ExceptionMessage.AUTH_ERROR);  // Bunu ExceptionMessage’a ekleyeceğiz birazdan
        setType(ExceptionMessage.TYPE_AUTH);    // Aynı şekilde
        setStatus(HttpStatus.BAD_REQUEST.value()); // 400 bad request
    }
}
