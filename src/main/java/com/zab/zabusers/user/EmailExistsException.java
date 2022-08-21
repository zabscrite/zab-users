package com.zab.zabusers.user;

import lombok.Getter;

public class EmailExistsException extends SignupException {

    @Getter
    private String email;

    public EmailExistsException(String email) {
        super("Email already exists.");
        this.email = email;
    }

    @Override
    public String getNamespace() {
        return super.getNamespace() + ".email-exists";
    }
}
