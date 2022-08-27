package com.zab.zabusers.subscription.domain.entity;

import com.zab.zabusers.team.domain.entity.Team;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subscription_plans")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SubscriptionPlan {

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
}
