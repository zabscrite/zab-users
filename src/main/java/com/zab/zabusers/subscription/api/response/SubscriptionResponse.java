package com.zab.zabusers.subscription.api.response;

import com.zab.zabusers.subscription.domain.entity.Subscription;
import lombok.Getter;

import java.util.Date;

@Getter
public class SubscriptionResponse {

    private Long id;

    private SubscriptionPlanResponse plan;

    private CustomerResponse customer;

    private Date effectivityDate;

    private Date expirationDate;

    private String status;

    public SubscriptionResponse(Subscription subscription) {
        id = subscription.getId();
        plan = new SubscriptionPlanResponse(subscription.getPlan());
        customer = new CustomerResponse(subscription.getCustomer());

        effectivityDate = subscription.getEffectivityDate();
        expirationDate = subscription.getExpirationDate();
        status = subscription.getStatus().name();
    }
}
