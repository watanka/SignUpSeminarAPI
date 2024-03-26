package com.SeminarRegistration.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class Seminar{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @OneToMany(mappedBy="seminars")
    private List<AppUser> appUsers;
    private int maxCapacity;
    private long openTime;

    public Seminar(int maxCapacity, long openTime) {
        this.maxCapacity = maxCapacity;
        this.openTime = openTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAppUsers(List<AppUser> appUsers) {
        this.appUsers = appUsers;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setOpenTime(long openTime) {
        this.openTime = openTime;
    }
}
