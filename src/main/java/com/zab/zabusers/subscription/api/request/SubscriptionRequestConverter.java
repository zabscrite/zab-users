package com.zab.zabusers.subscription.api.request;

import com.zab.zabusers.shared.auth.security.LoginContextService;
import com.zab.zabusers.shared.common.api.request.EntityFieldNotFoundException;
import com.zab.zabusers.subscription.domain.Customer;
import com.zab.zabusers.subscription.domain.CustomerRepository;
import com.zab.zabusers.subscription.domain.SubscriptionPlan;
import com.zab.zabusers.subscription.domain.SubscriptionPlanRepository;
import com.zab.zabusers.subscription.domain.SubscriptionRequestCommand;
import com.zab.zabusers.team.domain.Team;
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

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new EntityFieldNotFoundException(Customer.class, "customer", request.getCustomerId()));
        if (!team.equals(customer.getTeam())) {
            throw new EntityFieldNotFoundException(Customer.class, "customer", request.getCustomerId());
        }
        command.setCustomer(customer);

        SubscriptionPlan plan = subscriptionPlanRepository.findById(request.getSubscriptionPlanId())
                .orElseThrow(() -> new EntityFieldNotFoundException(SubscriptionPlan.class, "plan", request.getSubscriptionPlanId()));
        if (!team.equals(plan.getTeam())) {
            throw new EntityFieldNotFoundException(SubscriptionPlan.class, "plan", request.getSubscriptionPlanId());
        }
        command.setPlan(plan);

        return command;
    }
}
