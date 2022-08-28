package com.zab.zabusers.subscription.api.controller;

import com.zab.zabusers.subscription.api.request.SubscriptionRequest;
import com.zab.zabusers.subscription.api.request.SubscriptionRequestConverter;
import com.zab.zabusers.subscription.api.response.SubscriptionResponse;
import com.zab.zabusers.subscription.domain.entity.Subscription;
import com.zab.zabusers.subscription.domain.subscription.ExpiredSubscriptionsService;
import com.zab.zabusers.subscription.domain.subscription.SubscribeService;
import com.zab.zabusers.subscription.domain.subscription.SubscriptionRequestCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionApiController {


    Logger logger = LoggerFactory.getLogger(SubscriptionApiController.class);

    @Autowired
    private SubscriptionRequestConverter converter;

    @Autowired
    private SubscribeService subscribeService;

    @Autowired
    private ExpiredSubscriptionsService expiredSubscriptionsService;

    @PostMapping
    public SubscriptionResponse create(@RequestBody @Valid SubscriptionRequest request) throws Exception {
        SubscriptionRequestCommand command = converter.convert(request);
        Subscription subscription = subscribeService.subscribe(command);

        return new SubscriptionResponse(subscription);
    }

    @PostMapping("/close")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void closeExpiredSubscriptions() {
        logger.info("Closing expired subscriptions.");
        expiredSubscriptionsService.closeExpiredSubscriptions();

    }

}
