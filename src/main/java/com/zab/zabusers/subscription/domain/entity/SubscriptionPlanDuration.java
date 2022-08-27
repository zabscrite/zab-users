package com.zab.zabusers.subscription.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Getter
@AllArgsConstructor
public class SubscriptionPlanDuration {

    enum Unit {DAY, MONTH, YEAR, OPEN}


    private Integer value;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    // for JPA mapping
    private SubscriptionPlanDuration() {
        value = null;
        unit = null;
    }

    public Date computeExpirationDate(Date startDate) {
        return new Date();
    }
}
