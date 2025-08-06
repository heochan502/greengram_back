package com.green.greengram.config.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Slf4j
@RestControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //validation
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid (MethodArgumentNotValidException ex
                                                         , HttpHeaders headers, HttpStatusCode statusCode,
                                                         WebRequest request){
        return null;

    }
    private List<ValidationErrorResponse.ValidationError> getValidationError (BindException e) {

    }


}
