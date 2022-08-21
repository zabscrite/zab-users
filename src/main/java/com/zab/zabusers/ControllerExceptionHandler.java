package com.zab.zabusers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException exception) {
        ErrorResponse response = new ErrorResponse();
        for (ObjectError error : exception.getBindingResult().getAllErrors()) {
            response.addValidationError((FieldError) error);
        }

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
