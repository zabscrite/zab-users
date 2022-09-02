package com.zab.zabusers.subscription_plan.api.response;

import com.zab.zabusers.subscription_plan.domain.entity.SubscriptionPlanDuration;
import lombok.Getter;

@Getter
class SubscriptionPlanDurationResponse {

    private Integer value;

    private SubscriptionPlanDuration.Unit unit;

    public SubscriptionPlanDurationResponse(SubscriptionPlanDuration duration) {
        value = duration.getValue();
        unit = duration.getUnit();
    }
}
