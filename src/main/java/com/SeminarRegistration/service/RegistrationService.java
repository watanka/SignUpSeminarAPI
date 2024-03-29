package com.SeminarRegistration.service;
import com.SeminarRegistration.domain.Registration;
import com.SeminarRegistration.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RegistrationService {

    RegistrationRepository registrationRepository;
    RegisterPolicy registerPolicy;

    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository,
                               RegisterPolicy registerPolicy) {
        this.registrationRepository = registrationRepository;
        this.registerPolicy = registerPolicy;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void register(String userId, long seminarId) {


        Optional<Registration> registration = registrationRepository.findByUserIdAndSeminarId(userId, seminarId);
        // 등록 전 조건을 체크한다.
        registerPolicy.validate(registration);
        registrationRepository.save(userId, seminarId);
    }

    @Transactional(readOnly = true)
    public boolean checkRegistration(long seminarId, String userId) {
        return registrationRepository.findByUserIdAndSeminarId(userId, seminarId).isPresent();
    }
}
