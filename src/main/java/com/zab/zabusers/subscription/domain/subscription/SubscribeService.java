package com.zab.zabusers.subscription.domain.subscription;

import com.zab.zabusers.subscription.domain.entity.Subscription;
import com.zab.zabusers.subscription.domain.entity.SubscriptionPlan;
import com.zab.zabusers.subscription.domain.repository.SubscriptionRepository;
import com.zab.zabusers.subscription.domain.subscription.exception.SubscriptionException;
import com.zab.zabusers.team.domain.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class SubscribeService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private SubscriptionValidator subscriptionValidator;

    public Subscription subscribe(SubscriptionRequestCommand command) throws SubscriptionException {
        subscriptionValidator.validate(command);

        Subscription subscription = command.getSubscription();
        setEffectivityDate(command, subscription);
        setExpiration(command, subscription);
        setStatus(command, subscription);

        subscriptionRepository.save(subscription);
        return subscription;
    }

    public Page<Subscription> fetchAllSubscriptionsByTeam(Team team, Pageable pageable) {
        return subscriptionRepository.findAllByTeam(team, pageable);
    }

    public Optional<Subscription> fetchByIdAndTeam(Long id, Team team) {
        return subscriptionRepository.findByIdAndTeam(id, team);
    }

    private void setEffectivityDate(SubscriptionRequestCommand command, Subscription subscription) {
        LocalDateTime utcTime = toUtcLocalDateTime(command.getEffectivityDate());
        Date normalizedTime = Date.from(utcTime.atZone(ZoneId.of("UTC")).toInstant());

        subscription.setEffectivityDate(normalizedTime);
    }

    private void setExpiration(SubscriptionRequestCommand command, Subscription subscription) {
        SubscriptionPlan plan = command.getPlan();
        Date expirationDate = plan.computeExpiration(command.getEffectivityDate());
        subscription.setExpirationDate(expirationDate);
    }

    private void setStatus(SubscriptionRequestCommand command, Subscription subscription) {
        LocalDateTime now = toUtcLocalDateTime(Instant.now());
        LocalDateTime expirationDate = toUtcLocalDateTime(subscription.getExpirationDate());
        if (expirationDate.isBefore(now)) {
            subscription.close();
            return;
        }

        LocalDateTime effectivityDate = toUtcLocalDateTime(command.getEffectivityDate());
        if (effectivityDate.isBefore(now)) {
            subscription.open();
        }
    }

    private LocalDateTime toUtcLocalDateTime(Date date) {
        return toUtcLocalDateTime(Instant.ofEpochMilli(date.getTime()));
    }

    private LocalDateTime toUtcLocalDateTime(Instant instant) {
        return instant.atZone(ZoneId.of("UTC")).toLocalDateTime();
    }
}
