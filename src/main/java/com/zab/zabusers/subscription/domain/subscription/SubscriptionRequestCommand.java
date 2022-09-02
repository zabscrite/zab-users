package com.zab.zabusers.subscription.domain.subscription;

import com.zab.zabusers.subscription.domain.entity.Customer;
import com.zab.zabusers.subscription.domain.entity.Subscription;
import com.zab.zabusers.subscription_plan.domain.entity.SubscriptionPlan;
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

        return subscription;
    }
}
