package com.SeminarRegistration.repository;

import com.SeminarRegistration.domain.Registration;
import com.SeminarRegistration.domain.User;

import java.util.List;
import java.util.Optional;


public interface RegistrationRepository {

    public Registration save(long seminarId, String userId);
    public List<User> getAllUsers(long seminarId);
    public Optional<User> findUserById(long seminarId, String userId);

}
