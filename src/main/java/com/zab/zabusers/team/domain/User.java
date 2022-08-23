package com.zab.zabusers.team.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

    public enum Status {NOT_ACTIVATED, ACTIVE, DELETED}

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "users_id_seq")
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateCreated;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(255) default 'NOT_ACTIVATED'")
    private Status status = Status.NOT_ACTIVATED;

}
