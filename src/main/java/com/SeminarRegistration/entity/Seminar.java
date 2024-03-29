package com.SeminarRegistration.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Entity
public class Seminar{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Setter
    private ZonedDateTime openDate;

    @Column(name="max_registration_Num")
    private long maxRegistrationNum;

    private long currentRegistrationCount = 0;

    public Seminar(ZonedDateTime openDate, long maxRegistrationNum) {
        this.openDate = openDate;
        this.maxRegistrationNum = maxRegistrationNum;
    }

    public long getMaxRegistrationNum(){
        return maxRegistrationNum;
    };
    public long getCurrentRegistrationCount(){
        return currentRegistrationCount;
    };

    public void updateCurrentRegistrationCount(long updateNum){
        currentRegistrationCount = updateNum;
    }

}
