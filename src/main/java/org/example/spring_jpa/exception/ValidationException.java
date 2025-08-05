package org.example.spring_jpa.exception;

import org.example.spring_jpa.reponses.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidationException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));
        return new ValidationErrorResponse(HttpStatus.BAD_REQUEST.value(),"Validation failed", fieldErrors);
    }


    @ExceptionHandler(DuplicateFieldException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleDuplicateFieldException(DuplicateFieldException ex) {
        return ValidationErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Duplicate field")
                .fieldErrors(ex.getFieldErrors())
                .build();
    }
}
