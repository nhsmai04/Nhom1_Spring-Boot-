package org.example.spring_jpa.exception;

import lombok.*;

import java.util.Map;

@Getter
public class DuplicateFieldException extends RuntimeException{
    private  final Map<String,String> fieldErrors;
    public DuplicateFieldException(Map<String, String> fieldErrors) {
        super("Duplicate field found");
        this.fieldErrors = fieldErrors;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }
}
