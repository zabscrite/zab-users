package com.zab.zabusers.subscription_plan.domain.exception;


import com.zab.zabusers.subscription_plan.domain.entity.SubscriptionPlan;
import lombok.Getter;

public class PlanArchivalException extends SubscriptionPlanManagementException {

    @Getter
    private Long planId;

    @Getter
    private Integer subscriptionCount;


    public PlanArchivalException(SubscriptionPlan plan, int subscriptionCount) {
        super(String.format("Unable to archive plan %s with %d subscriptions", plan.getId(), subscriptionCount));
        this.planId = plan.getId();
        this.subscriptionCount = subscriptionCount;
    }

    @Override
    public String getNamespace() {
        return ".archive";
    }
}
