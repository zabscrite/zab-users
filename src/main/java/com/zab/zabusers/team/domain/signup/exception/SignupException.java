package com.zab.zabusers.team.domain.signup.exception;

import com.zab.zabusers.shared.common.domain.ZabBusinessRuleException;

public abstract class SignupException extends ZabBusinessRuleException {
    public SignupException(String s) {
        super(s);
    }

    @Override
    public String getNamespace() {
        return ".sign-up";
    }
}
