package com.zab.zabusers.subscription_plan.domain.exception;


import com.zab.zabusers.subscription_plan.domain.entity.SubscriptionPlan;
import lombok.Getter;

public class PlanActivationException extends SubscriptionPlanManagementException {

    @Getter
    private Long planId;

    @Getter
    private SubscriptionPlan.Status status;

    public PlanActivationException(SubscriptionPlan plan) {
        super(String.format("Unable to activate plan %s with status %s", plan.getId(), plan.getStatus()));
        planId = plan.getId();
        status = plan.getStatus();
    }

    @Override
    public String getNamespace() {
        return ".activate";
    }
}
