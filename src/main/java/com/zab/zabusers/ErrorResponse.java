package com.zab.zabusers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

    @Getter
    public List<ValidationError> errors;

    public ErrorResponse() {
        this.errors = new ArrayList<>();
    }

    public void addValidationError(FieldError error) {
        errors.add(new ValidationError(error));
    }

    @Getter
    @Setter
    private class ValidationError {
        private String field;

        private String code;

        private Object value;

        private String message;

        public ValidationError(FieldError error) {
            field = error.getField();
            code = error.getCode();
            value = error.getRejectedValue();
            message = error.getDefaultMessage();
        }
    }
}
