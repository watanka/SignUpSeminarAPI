package com.SeminarRegistration.seminarRegistration.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class AppUser {
    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @Getter
    @Column(name="userId")
    private String userId;

    @ManyToMany
    @JoinTable(
            name="user_seminar",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "seminar_id")
    )
    private List<Seminar> seminars;

    public AppUser(String userId) {
        this.userId = userId;
    }

    public void setSeminar(Seminar seminar) {
        seminars.add(seminar);
    }
}
