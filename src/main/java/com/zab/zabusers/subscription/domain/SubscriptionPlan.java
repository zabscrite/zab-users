package com.zab.zabusers.subscription.domain;

import com.zab.zabusers.team.domain.Team;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "subscription_plans")
@EntityListeners(AuditingEntityListener.class)
public class SubscriptionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "subscription_plans_id_seq")
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @ManyToOne(optional = false)
    private Team team;
}
