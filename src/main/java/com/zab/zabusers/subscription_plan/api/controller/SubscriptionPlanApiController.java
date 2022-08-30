package com.zab.zabusers.subscription_plan.api.controller;

import com.zab.zabusers.shared.auth.security.LoginContextService;
import com.zab.zabusers.shared.common.domain.ResourceNotFoundException;
import com.zab.zabusers.subscription_plan.api.request.CreateSubscriptionPlanRequest;
import com.zab.zabusers.subscription_plan.api.request.CreateSubscriptionPlanRequestConverter;
import com.zab.zabusers.subscription_plan.api.response.SubscriptionPlanResponse;
import com.zab.zabusers.subscription_plan.domain.entity.SubscriptionPlan;
import com.zab.zabusers.subscription_plan.domain.service.CreateSubscriptionPlanCommand;
import com.zab.zabusers.subscription_plan.domain.service.SubscriptionPlanService;
import com.zab.zabusers.team.domain.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/subscription-plans")
public class SubscriptionPlanApiController {

    @Autowired
    private CreateSubscriptionPlanRequestConverter subscriptionPlanRequestConverter;

    @Autowired
    private SubscriptionPlanService subscriptionPlanService;

    @Autowired
    private LoginContextService loginContextService;

    @PostMapping
    public SubscriptionPlanResponse create(@RequestBody @Valid CreateSubscriptionPlanRequest request) {
        CreateSubscriptionPlanCommand command = subscriptionPlanRequestConverter.convert(request);
        SubscriptionPlan plan = subscriptionPlanService.createPlan(command);
        return new SubscriptionPlanResponse(plan);
    }

    @GetMapping
    public List<SubscriptionPlanResponse> list() {
        Team team = loginContextService.getCurrentTeam();
        List<SubscriptionPlan> subscriptionPlans = subscriptionPlanService.fetchAllByTeam(team);

        return subscriptionPlans.stream()
                .map(SubscriptionPlanResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public SubscriptionPlanResponse get(@PathVariable long id) throws ResourceNotFoundException {
        Team team = loginContextService.getCurrentTeam();
        SubscriptionPlan plan = subscriptionPlanService.fetchByIdAndTeam(id, team)
                .orElseThrow(() -> new ResourceNotFoundException(SubscriptionPlan.class, id));

        return new SubscriptionPlanResponse(plan);
    }
}
