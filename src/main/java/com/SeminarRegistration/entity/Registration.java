package com.SeminarRegistration.entity;

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

        @Getter
        @Column(name="max_registration_num")
        private long maxRegistrationNum;

        @Getter
        @Column(name="current_registration_count")
        private long currRegistrationCount = 0;

    public Registration(String userId, long seminarId) {
        this.seminarId = seminarId;
        this.userId = userId;
    }

    public void updateCurrentRegistrationCount(long updateCount){
        currRegistrationCount = updateCount;
    }



}
