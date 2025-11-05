package com.etiya.common.crosscuttingconcerns.handlers;


import com.etiya.common.crosscuttingconcerns.exceptions.constants.ExceptionMessage;
import com.etiya.common.crosscuttingconcerns.exceptions.problemdetails.BusinessProblemDetails;
import com.etiya.common.crosscuttingconcerns.exceptions.problemdetails.InternalServerProblemDetails;
import com.etiya.common.crosscuttingconcerns.exceptions.problemdetails.ProblemDetails;
import com.etiya.common.crosscuttingconcerns.exceptions.problemdetails.ValidationProblemDetails;
import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.common.crosscuttingconcerns.exceptions.types.InternalServerException;
import com.etiya.common.crosscuttingconcerns.exceptions.types.AccessDeniedException;
import com.etiya.common.crosscuttingconcerns.exceptions.types.AuthException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Hidden
@RestControllerAdvice // Tüm rest controllerlara ortak davranışı uygulayacak anotasyon
public class GlobalExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetails handleBusinessException(BusinessException businessException) {
        BusinessProblemDetails businessProblemDetails = new BusinessProblemDetails();
        businessProblemDetails.setDetail(businessException.getMessage());
        return businessProblemDetails;
    }

    @ExceptionHandler({InternalServerException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ProblemDetails handleInternalServerException(InternalServerException internalServerException) {
        InternalServerProblemDetails internalServerProblemDetails = new InternalServerProblemDetails();
        internalServerProblemDetails.setDetail(internalServerException.getMessage());
        return internalServerProblemDetails;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetails handleValidationException(MethodArgumentNotValidException exception) {
        ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
        validationProblemDetails.setDetail(ExceptionMessage.VALIDATION_ERRORS);

        Map<String, String> validationErrors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            validationErrors.put(error.getField(), error.getDefaultMessage());
        });
        validationProblemDetails.setValidationErrors(validationErrors);
        return validationProblemDetails;
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetails handleException(Exception exception){
        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setTitle("Exception failed");
        problemDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        problemDetails.setType(ExceptionMessage.TYPE_EXCEPTION);
        problemDetails.setDetail(exception.getMessage());
        return problemDetails;
    }

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ProblemDetails handleUnauthorizedException(AuthException exception) {
        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setDetail(exception.getMessage());
        return problemDetails;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ProblemDetails handleForbiddenException(AccessDeniedException exception) {
        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setDetail(exception.getMessage());
        return problemDetails;
    }
}
