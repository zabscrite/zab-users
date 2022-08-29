package com.zab.zabusers.subscription.api.controller;

import com.zab.zabusers.shared.auth.security.LoginContextService;
import com.zab.zabusers.shared.common.domain.ResourceNotFoundException;
import com.zab.zabusers.subscription.api.request.SubscriptionRequest;
import com.zab.zabusers.subscription.api.request.SubscriptionRequestConverter;
import com.zab.zabusers.subscription.api.response.SubscriptionResponse;
import com.zab.zabusers.subscription.domain.entity.Subscription;
import com.zab.zabusers.subscription.domain.subscription.ExpiredSubscriptionsService;
import com.zab.zabusers.subscription.domain.subscription.SubscribeService;
import com.zab.zabusers.subscription.domain.subscription.SubscriptionRequestCommand;
import com.zab.zabusers.team.domain.entity.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionApiController {

    Logger logger = LoggerFactory.getLogger(SubscriptionApiController.class);

    @Autowired
    private SubscriptionRequestConverter converter;

    @Autowired
    private LoginContextService loginContextService;

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

    @GetMapping(path = "/{id}")
    public SubscriptionResponse get(@PathVariable long id) throws ResourceNotFoundException {
        Team team = loginContextService.getCurrentTeam();
        Subscription subscription = subscribeService.fetchByIdAndTeam(id, team)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return new SubscriptionResponse(subscription);
    }

    @GetMapping
    public List<SubscriptionResponse> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Team team = loginContextService.getCurrentTeam();
        PageRequest pageable = PageRequest.of(page, size);
        Page<Subscription> pagedSubscriptions = subscribeService.fetchAllSubscriptionsByTeam(team, pageable);

        return pagedSubscriptions.getContent().stream()
                .map(SubscriptionResponse::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/close")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void closeExpiredSubscriptions() {
        logger.info("Closing expired subscriptions.");
        expiredSubscriptionsService.closeExpiredSubscriptions();
    }

}
