package com.zab.zabusers.user.domain;

import com.zab.zabusers.ZabBusinessRuleException;

public abstract class SignupException extends ZabBusinessRuleException {
    public SignupException(String s) {
        super(s);
    }

    @Override
    public String getNamespace() {
        return ".sign-up";
    }
}
