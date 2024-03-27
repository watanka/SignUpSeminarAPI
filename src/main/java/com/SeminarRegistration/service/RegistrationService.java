package com.SeminarRegistration.service;

import com.SeminarRegistration.domain.Registration;
import com.SeminarRegistration.domain.User;
import com.SeminarRegistration.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    RegistrationRepository registrationRepository;

    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public Registration register(long seminarId, String userId) {
        if (registrationRepository.findUserById(seminarId, userId).isPresent()){
            throw new IllegalStateException("이미 등록되어있습니다.");
        }
        if (registrationRepository.getAllUsers(seminarId).size() >= 30){
            throw new IllegalStateException("등록이 마감되었습니다.");
        }

        Registration registration = registrationRepository.save(seminarId, userId);

        return registration;
    }

    public boolean check(long seminarId, String userId) {
        return registrationRepository.findUserById(seminarId, userId).isPresent();
    }
}
