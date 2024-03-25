package com.SeminarRegistration.seminarRegistration.domain;

import jakarta.persistence.*;

@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name="userId")
    public String userId;

    public User(long id, String userId) {
        this.id = id;
        this.userId = userId;
    }
}
