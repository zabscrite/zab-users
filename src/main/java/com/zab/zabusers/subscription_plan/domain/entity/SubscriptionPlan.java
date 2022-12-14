package com.zab.zabusers.subscription_plan.domain.entity;

import com.zab.zabusers.subscription_plan.domain.exception.PlanActivationException;
import com.zab.zabusers.subscription_plan.domain.exception.PlanDeactivationException;
import com.zab.zabusers.team.domain.entity.Team;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "subscription_plans")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SubscriptionPlan {

    public enum Status {DRAFT, ACTIVE, INACTIVE, ARCHIVED;}

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "subscription_plans_id_seq")
    @EqualsAndHashCode.Include
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @ManyToOne(optional = false)
    private Team team;

    @Getter
    @Setter
    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "value", column = @Column(name = "duration_value")),
            @AttributeOverride(name = "unit", column = @Column(name = "duration_unit"))
    })
    private SubscriptionPlanDuration duration;

    @Getter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.DRAFT;

    public Date computeExpiration(Date startDate) {
        SubscriptionPlanDuration duration = getDuration();
        return duration.calculateExpiration(startDate);
    }

    public void activate() throws PlanActivationException {
        if (!isActivatable()) {
            throw new PlanActivationException(this);
        }

        status = Status.ACTIVE;
    }

    public void deactivate() throws PlanDeactivationException {
        if (status != Status.ACTIVE) {
            throw new PlanDeactivationException(this);
        }

        status = Status.INACTIVE;
    }

    public void archive() {
        status = Status.ARCHIVED;
    }

    private boolean isActivatable() {
        List<Status> allowedStatuses = Arrays.asList(Status.DRAFT, Status.INACTIVE);
        return allowedStatuses.contains(status);
    }

}
