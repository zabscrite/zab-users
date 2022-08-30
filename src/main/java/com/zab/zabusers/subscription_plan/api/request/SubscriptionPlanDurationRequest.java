package com.zab.zabusers.subscription_plan.api.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
class SubscriptionPlanDurationRequest {

    @NotNull
    @Positive
    private Integer value;

    @NotNull
    private String unit;
}
