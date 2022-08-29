package com.zab.zabusers.subscription_plan.domain.service;

import com.zab.zabusers.subscription_plan.domain.entity.SubscriptionPlan;
import com.zab.zabusers.subscription_plan.domain.repository.SubscriptionPlanRepository;
import com.zab.zabusers.team.domain.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionPlanService {

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    public List<SubscriptionPlan> fetchAllByTeam(Team team) {
        return subscriptionPlanRepository.findAllByTeam(team);
    }
}
