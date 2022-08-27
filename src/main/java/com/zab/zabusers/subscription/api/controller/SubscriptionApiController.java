package com.zab.zabusers.subscription.api.controller;

import com.zab.zabusers.subscription.api.request.SubscriptionRequest;
import com.zab.zabusers.subscription.api.request.SubscriptionRequestConverter;
import com.zab.zabusers.subscription.domain.SubscribeService;
import com.zab.zabusers.subscription.domain.Subscription;
import com.zab.zabusers.subscription.domain.SubscriptionRequestCommand;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SubscribeService subscribeService;

    @PostMapping
    public Subscription create(@RequestBody @Valid SubscriptionRequest request) throws Exception {
        SubscriptionRequestCommand command = converter.convert(request);
        Subscription subscription = subscribeService.subscribe(command);

        return subscription;
    }

}
