package com.SeminarRegistration.domain;

import jakarta.persistence.*;

@Entity
public class Registration{

        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        private long registrationId;
        @Column(name="userId")
        private String userId;
        @Column(name="seminarId")
        private long seminarId;

    public Registration(long seminarId, String userId) {
        this.seminarId = seminarId;
        this.userId = userId;
    }
}
