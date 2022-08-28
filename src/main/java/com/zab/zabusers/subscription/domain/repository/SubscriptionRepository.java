package com.zab.zabusers.subscription.domain.repository;

import com.zab.zabusers.subscription.domain.entity.Subscription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

    @Query("SELECT s FROM Subscription s " +
            "WHERE s.status = 'OPEN' AND s.expirationDate <= now()")
    Set<Subscription> findAllExpired();
}
