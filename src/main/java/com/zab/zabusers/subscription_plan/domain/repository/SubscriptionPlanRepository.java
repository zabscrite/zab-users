package com.zab.zabusers.subscription_plan.domain.repository;

import com.zab.zabusers.subscription_plan.domain.entity.SubscriptionPlan;
import com.zab.zabusers.team.domain.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionPlanRepository extends CrudRepository<SubscriptionPlan, Long> {
    List<SubscriptionPlan> findAllByTeam(Team team);
}
