package com.zab.zabusers.subscription_plan.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionPlanDuration {

    public enum Unit {
        MONTH(ChronoUnit.MONTHS),
        YEAR(ChronoUnit.YEARS);

        private ChronoUnit chronoUnit;

        Unit(ChronoUnit chronoUnit) {
            this.chronoUnit = chronoUnit;
        }
    }


    private Integer value;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    public Date calculateExpiration(Date effectivityDate) {
        ZoneId utcZone = ZoneId.of("UTC");
        LocalDate utc = Instant.ofEpochMilli(effectivityDate.getTime())
                .atZone(utcZone)
                .toLocalDate();
        LocalDate expirationDate = utc.plus(value, unit.chronoUnit);

        return Date.from(expirationDate.atStartOfDay().atZone(utcZone).toInstant());
    }
}
