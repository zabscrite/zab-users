package com.zab.zabusers.subscription.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionRequestCommand {

    private Customer customer;

    private SubscriptionPlan plan;

}
