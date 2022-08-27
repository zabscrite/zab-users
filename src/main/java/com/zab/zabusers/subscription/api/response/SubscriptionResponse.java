package com.zab.zabusers.subscription.api.response;

import com.zab.zabusers.subscription.domain.Subscription;
import lombok.Getter;

import java.util.Date;

@Getter
public class SubscriptionResponse {

    private SubscriptionPlanResponse plan;

    private CustomerResponse customer;

    private Date effectivityDate;

    private Date expirationDate;

    public SubscriptionResponse(Subscription subscription) {
        plan = new SubscriptionPlanResponse(subscription.getPlan());
        customer = new CustomerResponse(subscription.getCustomer());

        effectivityDate = subscription.getEffectivityDate();
        expirationDate = subscription.getExpirationDate();
    }
}
