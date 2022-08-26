package com.zab.zabusers.subscription.api.controller;

import com.zab.zabusers.subscription.api.request.SubscriptionRequest;
import com.zab.zabusers.subscription.api.request.SubscriptionRequestConverter;
import com.zab.zabusers.subscription.domain.Subscription;
import com.zab.zabusers.subscription.domain.SubscriptionRequestCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionApiController {

    @Autowired
    private SubscriptionRequestConverter converter;

    @PostMapping
    public Subscription create(
            @CurrentSecurityContext(expression = "authentication.principal") Object currentUser,
            @RequestBody @Valid SubscriptionRequest request) throws Exception {
        SubscriptionRequestCommand command = converter.convert(request);

        Subscription subscription = new Subscription();
        subscription.setCustomer(command.getCustomer());
        subscription.setPlan(command.getPlan());

        return subscription;
    }
}
