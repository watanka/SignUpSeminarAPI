package com.SeminarRegistration.service;

import com.SeminarRegistration.entity.Seminar;
import com.SeminarRegistration.repository.SeminarRepository;

import java.util.Optional;

public class SeminarService {

    private SeminarRepository seminarRepository;
    private SeminarPolicy seminarPolicy;

    public SeminarService(SeminarRepository seminarRepository, SeminarPolicy seminarPolicy) {
        this.seminarRepository = seminarRepository;
        this.seminarPolicy = seminarPolicy;
    }


    public void updateRegistrationCount(long seminarId) {
        Optional<Seminar> findSeminar = seminarRepository.findById(seminarId);
        findSeminar.ifPresent(
                        seminar -> {seminarPolicy.validate(seminar);
                        seminar.updateCurrentRegistrationCount(seminar.getCurrentRegistrationCount() + 1);
        });


    }
}
