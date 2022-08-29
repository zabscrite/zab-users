package com.zab.zabusers.subscription.api.controller;

import com.zab.zabusers.shared.auth.security.LoginContextService;
import com.zab.zabusers.shared.common.domain.ResourceNotFoundException;
import com.zab.zabusers.subscription.api.request.SubscriptionRequest;
import com.zab.zabusers.subscription.api.request.SubscriptionRequestConverter;
import com.zab.zabusers.subscription.api.response.SubscriptionResponse;
import com.zab.zabusers.subscription.domain.entity.Subscription;
import com.zab.zabusers.subscription.domain.subscription.SubscriptionService;
import com.zab.zabusers.subscription.domain.subscription.SubscriptionRequestCommand;
import com.zab.zabusers.team.domain.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionApiController {


    @Autowired
    private SubscriptionRequestConverter converter;

    @Autowired
    private LoginContextService loginContextService;

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping
    public SubscriptionResponse create(@RequestBody @Valid SubscriptionRequest request) throws Exception {
        SubscriptionRequestCommand command = converter.convert(request);
        Subscription subscription = subscriptionService.subscribe(command);

        return new SubscriptionResponse(subscription);
    }

    @GetMapping(path = "/{id}")
    public SubscriptionResponse get(@PathVariable long id) throws ResourceNotFoundException {
        Team team = loginContextService.getCurrentTeam();
        Subscription subscription = subscriptionService.fetchByIdAndTeam(id, team)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return new SubscriptionResponse(subscription);
    }

    @GetMapping
    public List<SubscriptionResponse> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id:desc") String[] sort) {
        Team team = loginContextService.getCurrentTeam();
        PageRequest pageable = buildPageRequest(page, size, sort);
        Page<Subscription> pagedSubscriptions = subscriptionService.fetchAllSubscriptionsByTeam(team, pageable);

        return pagedSubscriptions.getContent().stream()
                .map(SubscriptionResponse::new)
                .collect(Collectors.toList());
    }


    private PageRequest buildPageRequest(int page, int size, String[] sortParameters) {
        Optional<Sort> sort = Arrays.stream(sortParameters)
                .map(parametersString -> {
                    List<String> parameters = Arrays.stream(parametersString.split(":"))
                            .map(String::trim)
                            .collect(Collectors.toList());
                    String fieldName = parameters.get(0);
                    Sort.Direction sortDirection = parameters.size() > 1
                            ? Sort.Direction.fromString(parameters.get(1))
                            : Sort.Direction.DESC;

                    return Sort.by(sortDirection, fieldName);
                }).reduce(Sort::and);

        if (sort.isPresent()) {
            return PageRequest.of(page, size, sort.get());
        } else {
            return PageRequest.of(page, size);
        }
    }

}
