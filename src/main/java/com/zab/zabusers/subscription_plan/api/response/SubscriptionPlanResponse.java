package com.zab.zabusers.subscription_plan.api.response;

import com.zab.zabusers.subscription_plan.domain.entity.SubscriptionPlan;
import lombok.Getter;

@Getter
public class SubscriptionPlanResponse {

    private Long id;

    private String name;

    private SubscriptionPlan.Status status;

    private SubscriptionPlanDurationResponse duration;


    public SubscriptionPlanResponse(SubscriptionPlan plan) {
        id = plan.getId();
        name = plan.getName();
        status = plan.getStatus();
        duration = new SubscriptionPlanDurationResponse(plan.getDuration());
    }
}
