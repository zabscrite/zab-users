package com.zab.zabusers.user.controller;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private int minLength;

    @Override
    public void initialize(Password parameters) {
        minLength = parameters.minLength();

        ConstraintValidator.super.initialize(parameters);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        if (value.length() < minLength) {
            return false;
        }

        return true;
    }
}
