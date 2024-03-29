package com.SeminarRegistration.repository;

import com.SeminarRegistration.domain.Registration;

import java.util.Optional;


public interface RegistrationRepository {

    public Registration save(String userId, long seminarId);
    public Optional<Registration> findByUserIdAndSeminarId(String userId, long seminarId);


}
