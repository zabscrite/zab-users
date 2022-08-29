package com.zab.zabusers.subscription.domain.repository;

import com.zab.zabusers.subscription.domain.entity.Subscription;
import com.zab.zabusers.team.domain.entity.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

    @Query("SELECT s FROM #{#entityName} s " +
            "WHERE s.status = 'OPEN' AND s.expirationDate <= now()")
    Set<Subscription> findAllExpired();

    Optional<Subscription> findByIdAndTeam(Long id, Team team);

    Page<Subscription> findAllByTeam(Team team, Pageable pageable);
}
