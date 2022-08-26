package com.zab.zabusers.subscription.api.request;

import com.zab.zabusers.shared.common.api.request.EntityFieldNotFoundException;
import com.zab.zabusers.subscription.domain.Customer;
import com.zab.zabusers.subscription.domain.CustomerRepository;
import com.zab.zabusers.subscription.domain.SubscriptionPlan;
import com.zab.zabusers.subscription.domain.SubscriptionPlanRepository;
import com.zab.zabusers.subscription.domain.SubscriptionRequestCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionRequestConverter {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;


    public SubscriptionRequestCommand convert(SubscriptionRequest request) throws Exception {
        // TODO: Add validation that customers and subscription plan bounded to current team
        SubscriptionRequestCommand command = new SubscriptionRequestCommand();

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new EntityFieldNotFoundException(Customer.class, "customer", request.getCustomerId()));
        command.setCustomer(customer);

        SubscriptionPlan plan = subscriptionPlanRepository.findById(request.getSubscriptionPlanId())
                .orElseThrow(() -> new Exception("Subscription plan not found"));
        command.setPlan(plan);

        return command;
    }
}
