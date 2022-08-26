package com.zab.zabusers.subscription.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "subscriptions")
@EntityListeners(AuditingEntityListener.class)
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "subscriptions_id_seq")
    private Long id;

    @Getter
    @Setter
    @ManyToOne(optional = false)
    private Customer customer;

    @Getter
    @Setter
    @ManyToOne(optional = false)
    private SubscriptionPlan plan;

    @Getter
    @Setter
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateCreated;
}
