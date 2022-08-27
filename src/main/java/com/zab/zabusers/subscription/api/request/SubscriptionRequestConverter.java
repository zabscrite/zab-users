package com.zab.zabusers.subscription.api.request;

import com.zab.zabusers.shared.auth.security.LoginContextService;
import com.zab.zabusers.shared.common.api.request.EntityFieldNotFoundException;
import com.zab.zabusers.subscription.domain.entity.Customer;
import com.zab.zabusers.subscription.domain.repository.CustomerRepository;
import com.zab.zabusers.subscription.domain.entity.SubscriptionPlan;
import com.zab.zabusers.subscription.domain.repository.SubscriptionPlanRepository;
import com.zab.zabusers.subscription.domain.subscription.SubscriptionRequestCommand;
import com.zab.zabusers.team.domain.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionRequestConverter {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    @Autowired
    private LoginContextService loginContextService;


    public SubscriptionRequestCommand convert(SubscriptionRequest request) throws Exception {
        Team team = loginContextService.getCurrentTeam();
        SubscriptionRequestCommand command = new SubscriptionRequestCommand();
        command.setCustomer(fetchCustomer(request, team));
        command.setPlan(fetchPlan(request, team));
        command.setEffectivityDate(request.getEffectivityDate());

        return command;
    }

    private Customer fetchCustomer(SubscriptionRequest request, Team team) throws EntityFieldNotFoundException {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new EntityFieldNotFoundException(Customer.class, "customer", request.getCustomerId()));
        if (!team.equals(customer.getTeam())) {
            throw new EntityFieldNotFoundException(Customer.class, "customer", request.getCustomerId());
        }
        return customer;
    }

    private SubscriptionPlan fetchPlan(SubscriptionRequest request, Team team) throws EntityFieldNotFoundException {
        SubscriptionPlan plan = subscriptionPlanRepository.findById(request.getPlanId())
                .orElseThrow(() -> new EntityFieldNotFoundException(SubscriptionPlan.class, "plan", request.getPlanId()));
        if (!team.equals(plan.getTeam())) {
            throw new EntityFieldNotFoundException(SubscriptionPlan.class, "plan", request.getPlanId());
        }
        return plan;
    }
}
