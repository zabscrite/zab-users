package com.zab.zabusers.subscription.domain.repository;

import com.zab.zabusers.subscription.domain.entity.Subscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
}
