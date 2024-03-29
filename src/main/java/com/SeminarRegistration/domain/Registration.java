package com.SeminarRegistration.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Registration{

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long registrationId;

        @Getter
        @Column(name="userId")
        private String userId;
        @Getter
        @Column(name="seminarId")
        private long seminarId;

    public Registration(String userId, long seminarId) {
        this.seminarId = seminarId;
        this.userId = userId;
    }
}
