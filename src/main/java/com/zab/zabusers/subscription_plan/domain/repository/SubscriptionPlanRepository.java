package com.zab.zabusers.subscription_plan.domain.repository;

import com.zab.zabusers.subscription_plan.domain.entity.SubscriptionPlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionPlanRepository extends CrudRepository<SubscriptionPlan, Long> {
}
