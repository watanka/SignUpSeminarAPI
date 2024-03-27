package com.SeminarRegistration.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @Column(name="userId")
    private String userId;

    public User(String userId) {
        this.userId = userId;
    }
}
