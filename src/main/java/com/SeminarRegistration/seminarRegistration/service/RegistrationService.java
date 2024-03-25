package com.SeminarRegistration.seminarRegistration.service;

import com.SeminarRegistration.seminarRegistration.domain.User;
import com.SeminarRegistration.seminarRegistration.repository.MemoryRegistrationRepository;
import com.SeminarRegistration.seminarRegistration.repository.RegistrationRepository;

import java.util.HashMap;

public class RegistrationService {

    RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public User register(long seminarId, String userId) {
        if (registrationRepository.findUserById(seminarId, userId).isPresent()){
            throw new IllegalStateException("이미 등록되어있습니다.");
        }
        if (registrationRepository.getAllUsers(seminarId).size() >= 30){
            throw new IllegalStateException("등록이 마감되었습니다.");
        }

        User user = registrationRepository.save(seminarId, userId);

        return user;
    }

    public boolean check(long seminarId, String userId) {
        return registrationRepository.findUserById(seminarId, userId).isPresent();
    }
}
