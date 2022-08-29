package com.zab.zabusers.subscription.domain.subscription;

import com.zab.zabusers.subscription.domain.entity.SubscriptionPlan;
import com.zab.zabusers.subscription.domain.subscription.exception.InactiveSubscriptionPlanException;
import com.zab.zabusers.subscription.domain.subscription.exception.SubscriptionException;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionValidator {
    public void validate(SubscriptionRequestCommand command) throws SubscriptionException {
        SubscriptionPlan plan = command.getPlan();
        if (plan.getStatus() != SubscriptionPlan.Status.ACTIVE) {
            throw new InactiveSubscriptionPlanException(plan);
        }

    }
}
