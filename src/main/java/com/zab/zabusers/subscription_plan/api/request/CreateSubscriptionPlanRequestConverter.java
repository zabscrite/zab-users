package com.zab.zabusers.subscription_plan.api.request;

import com.zab.zabusers.shared.auth.security.LoginContextService;
import com.zab.zabusers.subscription_plan.domain.entity.SubscriptionPlanDuration;
import com.zab.zabusers.subscription_plan.domain.service.CreateSubscriptionPlanCommand;
import com.zab.zabusers.team.domain.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateSubscriptionPlanRequestConverter {

    @Autowired
    private LoginContextService loginContextService;

    public CreateSubscriptionPlanCommand convert(CreateSubscriptionPlanRequest request) {
        Team team = loginContextService.getCurrentTeam();
        CreateSubscriptionPlanCommand command = new CreateSubscriptionPlanCommand();
        command.setTeam(team);
        command.setName(request.getName());
        setDuration(request, command);

        return command;
    }

    private void setDuration(CreateSubscriptionPlanRequest request, CreateSubscriptionPlanCommand command) {
        String unitString = request.getDurationUnit();
        SubscriptionPlanDuration.Unit durationUnit = SubscriptionPlanDuration.Unit.valueOf(unitString);

        command.setDuration(request.getDurationValue(), durationUnit);
    }
}
