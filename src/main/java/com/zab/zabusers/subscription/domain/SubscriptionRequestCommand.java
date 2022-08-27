package com.zab.zabusers.subscription.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SubscriptionRequestCommand {

    private Customer customer;

    private SubscriptionPlan plan;

    private Date effectivityDate;

    public Subscription getSubscription() {
        Subscription subscription = new Subscription();
        subscription.setCustomer(customer);
        subscription.setPlan(plan);
        subscription.setEffectivityDate(effectivityDate);

        return subscription;
    }
}
