package com.zab.zabusers.team.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "teams")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Team {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "teams_id_seq")
    @EqualsAndHashCode.Include
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String name;

    @Getter
    @Setter
    @OneToOne
    private User owner;

    @Getter
    @Setter
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created", nullable = false)
    private Date dateCreated;

}
