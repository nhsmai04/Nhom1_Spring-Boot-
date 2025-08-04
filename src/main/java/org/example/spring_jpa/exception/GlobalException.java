package org.example.spring_jpa.exception;


import org.example.spring_jpa.reponses.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalException {

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

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseObject> handleGeneralException(Exception e) {
            return ResponseEntity.internalServerError().body(
                    ResponseObject.builder()
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .message(e.getMessage())
                            .build()
            );
    }
}
