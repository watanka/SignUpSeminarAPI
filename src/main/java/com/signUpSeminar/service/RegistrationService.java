package com.signUpSeminar.service;

import com.signUpSeminar.domain.User;
import com.signUpSeminar.repository.MemoryRegistrationRepository;

public class RegistrationService {

    MemoryRegistrationRepository registrationRepository = new MemoryRegistrationRepository();

    public RegistrationService(MemoryRegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public User register(long seminarId, long userId) {
        if (registrationRepository.findUserById(seminarId, userId) != null){
            throw new IllegalStateException("이미 등록되어있습니다.");
        }
        if (registrationRepository.getAllUsers(seminarId).size() >= 30){
            throw new IllegalStateException("등록이 마감되었습니다.");
        }

        User user = new User(userId);
        registrationRepository.save(seminarId, user);

        return user;
    }

    public boolean check(long seminarId, long userId) {
        return registrationRepository.findUserById(seminarId, userId) != null;
    }
}
