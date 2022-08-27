package com.zab.zabusers.subscription.domain.subscription;

import com.zab.zabusers.subscription.domain.entity.Subscription;
import com.zab.zabusers.subscription.domain.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscribeService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public Subscription subscribe(SubscriptionRequestCommand command) {
        Subscription subscription = command.getSubscription();
        subscriptionRepository.save(subscription);
        return subscription;
    }
}
