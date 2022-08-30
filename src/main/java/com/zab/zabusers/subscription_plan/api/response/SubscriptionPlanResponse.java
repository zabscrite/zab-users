package com.zab.zabusers.subscription_plan.api.response;

import com.zab.zabusers.subscription_plan.domain.entity.SubscriptionPlan;
import lombok.Getter;

@Getter
public class SubscriptionPlanResponse {

    private Long id;

    private String name;

    private SubscriptionPlanDurationResponse duration;

    private SubscriptionPlan.Status status;


    public SubscriptionPlanResponse(SubscriptionPlan plan) {
        id = plan.getId();
        name = plan.getName();
        status = plan.getStatus();
        duration = new SubscriptionPlanDurationResponse(plan.getDuration());
    }
}
