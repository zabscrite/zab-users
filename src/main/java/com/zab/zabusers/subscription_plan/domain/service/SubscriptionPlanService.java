package com.zab.zabusers.subscription_plan.domain.service;

import com.zab.zabusers.subscription_plan.domain.entity.SubscriptionPlan;
import com.zab.zabusers.subscription_plan.domain.exception.PlanActivationException;
import com.zab.zabusers.subscription_plan.domain.exception.PlanDeactivationException;
import com.zab.zabusers.subscription_plan.domain.repository.SubscriptionPlanRepository;
import com.zab.zabusers.team.domain.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionPlanService {

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;


    public SubscriptionPlan createPlan(CreateSubscriptionPlanCommand command) {
        SubscriptionPlan plan = command.toSubscriptionPlan();
        subscriptionPlanRepository.save(plan);

        return plan;
    }

    public SubscriptionPlan activatePlan(SubscriptionPlan plan) throws PlanActivationException {
        plan.activate();
        subscriptionPlanRepository.save(plan);

        return plan;
    }

    public SubscriptionPlan deactivatePlan(SubscriptionPlan plan) throws PlanDeactivationException {
        plan.deactivate();
        subscriptionPlanRepository.save(plan);

        return plan;
    }

    public List<SubscriptionPlan> fetchAllByTeam(Team team) {
        return subscriptionPlanRepository.findAllByTeam(team);
    }

    public Optional<SubscriptionPlan> fetchByIdAndTeam(Long id, Team team) {
        return subscriptionPlanRepository.findByIdAndTeam(id, team);
    }
}
