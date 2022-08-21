package com.zab.zabusers;

public abstract class ZabBusinessRuleException extends Exception {

    public ZabBusinessRuleException(String s) {
        super(s);
    }

    public String getCode() {
        return "zab.zab-users" + getNamespace();
    }

    public abstract String getNamespace();
}
