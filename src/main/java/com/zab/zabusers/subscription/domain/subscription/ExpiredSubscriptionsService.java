package com.zab.zabusers.subscription.domain.subscription;

import com.zab.zabusers.subscription.domain.entity.Subscription;
import com.zab.zabusers.subscription.domain.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ExpiredSubscriptionsService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public void closeExpiredSubscriptions() {
        Set<Subscription> expiringSubscriptions = subscriptionRepository.findAllExpired();
        expiringSubscriptions.stream().forEach(Subscription::close);
        subscriptionRepository.saveAll(expiringSubscriptions);
    }
}
