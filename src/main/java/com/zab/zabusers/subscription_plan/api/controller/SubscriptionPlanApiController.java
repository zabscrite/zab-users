package com.zab.zabusers.subscription_plan.api.controller;

import com.zab.zabusers.shared.auth.security.LoginContextService;
import com.zab.zabusers.subscription_plan.api.response.SubscriptionPlanResponse;
import com.zab.zabusers.subscription_plan.domain.entity.SubscriptionPlan;
import com.zab.zabusers.subscription_plan.domain.service.SubscriptionPlanService;
import com.zab.zabusers.team.domain.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/subscription-plans")
public class SubscriptionPlanApiController {

    @Autowired
    private SubscriptionPlanService subscriptionPlanService;

    @Autowired
    private LoginContextService loginContextService;

    @GetMapping
    public List<SubscriptionPlanResponse> list() {
        Team team = loginContextService.getCurrentTeam();
        List<SubscriptionPlan> subscriptionPlans = subscriptionPlanService.fetchAllByTeam(team);
        return subscriptionPlans.stream().map(SubscriptionPlanResponse::new).collect(Collectors.toList());
    }
}
