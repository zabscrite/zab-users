package com.zab.zabusers.subscription.api.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class SubscriptionRequest {

    @NotNull
    @Positive
    private Long customerId;

    @NotNull
    @Positive
    private Long subscriptionPlanId;

}