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
                        .message("Có lỗi : không tìm thấy tài nguyen")
                        .data(null)
                        .build()
        );

    }

    // Xử lý validation exception


    // Xử lý email và phone đã tồn tại


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
