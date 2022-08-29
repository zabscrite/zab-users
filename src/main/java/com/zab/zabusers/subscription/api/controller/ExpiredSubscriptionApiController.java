package com.zab.zabusers.subscription.api.controller;

import com.zab.zabusers.subscription.domain.entity.Subscription;
import com.zab.zabusers.subscription.domain.subscription.ExpiredSubscriptionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/subscriptions/expired")
public class ExpiredSubscriptionApiController {

    @Autowired
    private ExpiredSubscriptionsService expiredSubscriptionsService;

    private Logger logger = LoggerFactory.getLogger(SubscriptionApiController.class);

    @PostMapping("/close")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void closeExpiredSubscriptions() {
        logger.info("Closing expired subscriptions.");
        Set<Subscription> expiredSubscriptions = expiredSubscriptionsService.closeExpiredSubscriptions();
        logger.info(String.format("Closed %d expired subscriptions.", expiredSubscriptions.size()));
    }
}
