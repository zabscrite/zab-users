package com.zab.zabusers.subscription.domain.subscription;

import com.zab.zabusers.subscription.domain.entity.Subscription;
import com.zab.zabusers.subscription.domain.entity.SubscriptionPlan;
import com.zab.zabusers.subscription.domain.repository.SubscriptionRepository;
import com.zab.zabusers.subscription.domain.subscription.exception.SubscriptionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SubscribeService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private SubscriptionValidator subscriptionValidator;

    public Subscription subscribe(SubscriptionRequestCommand command) throws SubscriptionException {
        subscriptionValidator.validate(command);

        Subscription subscription = command.getSubscription();
        setExpiration(command, subscription);

        subscriptionRepository.save(subscription);
        return subscription;
    }

    private void setExpiration(SubscriptionRequestCommand command, Subscription subscription) {
        SubscriptionPlan plan = command.getPlan();
        Date expirationDate = plan.computeExpiration(command.getEffectivityDate());
        subscription.setExpirationDate(expirationDate);
    }

}
