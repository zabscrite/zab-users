package com.zab.zabusers.subscription.domain.subscription;

import com.zab.zabusers.subscription.domain.entity.Subscription;
import com.zab.zabusers.subscription.domain.entity.SubscriptionPlan;
import com.zab.zabusers.subscription.domain.entity.SubscriptionPlanDuration;
import com.zab.zabusers.subscription.domain.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SubscribeService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public Subscription subscribe(SubscriptionRequestCommand command) {
        Subscription subscription = command.getSubscription();
        setExpiration(command, subscription);

        subscriptionRepository.save(subscription);
        return subscription;
    }

    private void setExpiration(SubscriptionRequestCommand command, Subscription subscription) {
        SubscriptionPlan plan = command.getPlan();
        SubscriptionPlanDuration duration = plan.getDuration();
        Date expirationDate = duration.calculateExpiration(command.getEffectivityDate());
        subscription.setExpirationDate(expirationDate);
    }

}
