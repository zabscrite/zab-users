package com.zab.zabusers.subscription_plan.domain.exception;

import com.zab.zabusers.shared.common.domain.ZabBusinessRuleException;

public abstract class SubscriptionPlanManagementException extends ZabBusinessRuleException {

    public SubscriptionPlanManagementException(String s) {
        super(s);
    }

    @Override
    public String getNamespace() {
        return ".subscription-plan";
    }
}
