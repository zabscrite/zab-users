package com.zab.zabusers.subscription.domain.entity;

import com.zab.zabusers.team.domain.entity.Team;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "subscriptions")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Subscription {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "subscriptions_id_seq")
    @EqualsAndHashCode.Include
    private Long id;

    @Getter
    @Setter
    @ManyToOne(optional = false)
    private Customer customer;

    @Getter
    @ManyToOne(optional = false)
    private SubscriptionPlan plan;

    @Getter
    @ManyToOne(optional = false)
    private Team team;

    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date effectivityDate;

    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date expirationDate;

    @Getter
    @Setter
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateCreated;

    public void setPlan(SubscriptionPlan plan) {
        this.plan = plan;
        this.team = plan.getTeam();
    }
}
