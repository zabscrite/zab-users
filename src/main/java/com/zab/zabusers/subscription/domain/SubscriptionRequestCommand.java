package com.zab.zabusers.subscription.domain;

import com.zab.zabusers.team.domain.Team;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionRequestCommand {

    private Team team;

    private Customer customer;

    private SubscriptionPlan plan;

}
