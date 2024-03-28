package com.SeminarRegistration.service;

import com.SeminarRegistration.domain.RegisterPolicy;
import com.SeminarRegistration.domain.Registration;
import com.SeminarRegistration.domain.User;
import com.SeminarRegistration.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

    RegistrationRepository registrationRepository;
    RegisterPolicy registerPolicyImpl;

    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository, RegisterPolicy registerPolicyImpl) {
        this.registrationRepository = registrationRepository;
        this.registerPolicyImpl = registerPolicyImpl;
    }

    public Registration register(long seminarId, String userId) {
        Optional<User> registerList = registrationRepository.findUserById(seminarId, userId);
        long currentEnrollmentCount = registrationRepository.getCurrentEnrollmentCount(seminarId);
        long maxEnrollment = registrationRepository.getMaxEnrollmentNum();


        if (registerPolicyImpl.validate(userId, currentEnrollmentCount, maxEnrollment, registerList)){
            return registrationRepository.save(seminarId, userId);
        }
    }

    public boolean check(long seminarId, String userId) {
        return registrationRepository.findUserById(seminarId, userId).isPresent();
    }
}
