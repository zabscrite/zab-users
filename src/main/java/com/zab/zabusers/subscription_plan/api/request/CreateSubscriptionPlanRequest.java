package com.zab.zabusers.subscription_plan.api.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class CreateSubscriptionPlanRequest {

    @NotNull
    private String name;

    @NotNull
    private SubscriptionPlanDurationRequest duration;

    public @NotNull @Positive Integer getDurationValue() {
        return duration.getValue();
    }

    public @NotNull String getDurationUnit() {
        return duration.getUnit();
    }

}
