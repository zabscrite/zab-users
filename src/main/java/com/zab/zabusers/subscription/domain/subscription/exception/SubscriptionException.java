package com.zab.zabusers.subscription.domain.subscription.exception;

import com.zab.zabusers.shared.common.domain.ZabBusinessRuleException;

public abstract class SubscriptionException extends ZabBusinessRuleException {
    public SubscriptionException(String s) {
        super(s);
    }

    @Override
    public String getNamespace() {
        return ".subscription";
    }
}
