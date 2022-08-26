package com.zab.zabusers.subscription.api.controller;

import com.zab.zabusers.subscription.api.request.SubscriptionRequest;
import com.zab.zabusers.subscription.domain.Subscription;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionApiController {

    @PostMapping
    public Subscription create(@RequestBody @Valid SubscriptionRequest request) {
        Subscription subscription = new Subscription();

        return subscription;
    }
}
