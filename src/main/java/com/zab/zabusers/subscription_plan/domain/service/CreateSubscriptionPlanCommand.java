package com.zab.zabusers.subscription_plan.domain.service;

import com.zab.zabusers.subscription_plan.domain.entity.SubscriptionPlan;
import com.zab.zabusers.subscription_plan.domain.entity.SubscriptionPlanDuration;
import com.zab.zabusers.team.domain.entity.Team;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSubscriptionPlanCommand {

    private Team team;

    private String name;

    private SubscriptionPlanDurationCommand duration = new SubscriptionPlanDurationCommand();

    public void setDuration(Integer value, SubscriptionPlanDuration.Unit unit) {
        duration.setValue(value);
        duration.setUnit(unit);
    }

    @Getter
    @Setter
    class SubscriptionPlanDurationCommand {

        private Integer value;

        private SubscriptionPlanDuration.Unit unit;

        public SubscriptionPlanDuration toSubscriptionPlanDuration() {
            return new SubscriptionPlanDuration(value, unit);
        }
    }

    public SubscriptionPlan toSubscriptionPlan() {
        SubscriptionPlan plan = new SubscriptionPlan();
        plan.setTeam(team);
        plan.setName(name);
        plan.setDuration(duration.toSubscriptionPlanDuration());

        return plan;
    }
}
