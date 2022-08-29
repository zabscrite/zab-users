package com.zab.zabusers.shared.common.api.controller;

import com.zab.zabusers.shared.common.api.request.EntityFieldNotFoundException;
import com.zab.zabusers.shared.common.domain.ResourceNotFoundException;
import com.zab.zabusers.shared.common.domain.ZabBusinessRuleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    // REST exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return new ResponseEntity<>(new LinkedHashMap<String, Object>() {{
            put("code", "resource.not_found");
            put("message", "Resource not found.");
            put("id", exception.getId());
        }}, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException exception) {
        ErrorResponse response = new ErrorResponse();
        for (ObjectError error : exception.getBindingResult().getAllErrors()) {
            response.addValidationError((FieldError) error);
        }

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ZabBusinessRuleException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessRuleException(ZabBusinessRuleException exception) {
        Map<String, Object> arguments = Arrays.stream(exception.getClass().getDeclaredFields())
                .collect(Collectors.toMap(Field::getName, field -> {
                    try {
                        field.setAccessible(true);
                        return field.get(exception);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }));

        return new ResponseEntity<>(new LinkedHashMap<String, Object>() {{
            put("code", exception.getCode());
            put("message", exception.getMessage());
            put("arguments", arguments);
        }}, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(EntityFieldNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEntityFieldNotFoundException(EntityFieldNotFoundException exception) {
        return new ResponseEntity<>(new LinkedHashMap<String, Object>() {{
            put("message", exception.getMessage());
            put("field", exception.getFieldName());
            put("value", exception.getId());
        }}, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
