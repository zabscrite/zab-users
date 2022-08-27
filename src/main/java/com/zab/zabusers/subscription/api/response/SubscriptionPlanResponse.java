package com.zab.zabusers.subscription.api.response;

import com.zab.zabusers.subscription.domain.SubscriptionPlan;
import lombok.Getter;

@Getter
public class SubscriptionPlanResponse {

    private Long id;

    private String name;

    public SubscriptionPlanResponse(SubscriptionPlan plan) {
        id = plan.getId();
        name = plan.getName();
    }
}
