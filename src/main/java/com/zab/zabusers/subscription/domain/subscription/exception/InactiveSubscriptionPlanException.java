package com.zab.zabusers.subscription.domain.subscription.exception;

import com.zab.zabusers.subscription.domain.entity.SubscriptionPlan;
import lombok.Getter;

public class InactiveSubscriptionPlanException extends SubscriptionException {

    @Getter
    private SubscriptionPlan plan;

    public InactiveSubscriptionPlanException(SubscriptionPlan plan) {
        super(String.format("Subscription plan %s is inactive", plan.getId()));
        this.plan = plan;
    }

    @Override
    public String getNamespace() {
        return super.getNamespace() + ".plan.inactive";
    }
}
