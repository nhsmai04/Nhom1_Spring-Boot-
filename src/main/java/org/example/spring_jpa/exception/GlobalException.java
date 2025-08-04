package org.example.spring_jpa.exception;


import org.example.spring_jpa.reponses.ResponseObject;
import org.example.spring_jpa.reponses.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    // Xử lý các trường hợp không tìm thấy tài nguyên
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseObject> handleNotFoundException(NotFoundException ex) {
     return   ResponseEntity.badRequest().body(
                ResponseObject.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .message(ex.getMessage())
                        .data(null)
                        .build()
        );

    }

    // Xử lý validation exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));
        return new ValidationErrorResponse(HttpStatus.BAD_REQUEST.value(),"Validation failed", fieldErrors);
    }

    // Xử lý email và phone đã tồn tại
    @ExceptionHandler(DuplicateFieldException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleDuplicateFieldException(DuplicateFieldException ex) {
        return ValidationErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Duplicate field")
                .fieldErrors(ex.getFieldErrors())
                .build();
    }

    // Fallback
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseObject> handleGeneralException(Exception e) {
        return ResponseEntity.internalServerError().body(
                ResponseObject.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message("Có lỗi xảy ra rồi ní ơi ")
                        .build()
        );
    }

}
