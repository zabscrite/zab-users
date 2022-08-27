package com.zab.zabusers.subscription.api.response;

import com.zab.zabusers.subscription.domain.Subscription;
import lombok.Getter;

@Getter
public class SubscriptionResponse {

    private SubscriptionPlanResponse plan;

    private CustomerResponse customer;

    public SubscriptionResponse(Subscription subscription) {
        plan = new SubscriptionPlanResponse(subscription.getPlan());
        customer = new CustomerResponse(subscription.getCustomer());
    }
}
