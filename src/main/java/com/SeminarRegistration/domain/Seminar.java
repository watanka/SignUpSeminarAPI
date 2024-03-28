package com.SeminarRegistration.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
public class Seminar{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Setter
    private long openTime;

    @Column(name="maxEnrollmentNum")
    private long maxEnrollmentNum;

    public Seminar(long openTime, long maxEnrollmentNum) {
        this.openTime = openTime;
        this.maxEnrollmentNum = maxEnrollmentNum;
    }

}
