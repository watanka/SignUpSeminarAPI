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

    public Seminar(long openTime) {
        this.openTime = openTime;
    }

}
