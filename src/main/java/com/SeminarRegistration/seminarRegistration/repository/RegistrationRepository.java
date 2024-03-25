package com.SeminarRegistration.seminarRegistration.repository;

import com.SeminarRegistration.seminarRegistration.domain.User;

import java.util.List;
import java.util.Optional;

public interface RegistrationRepository {

    public User save(long seminarId, String userId);
    public List<User> getAllUsers(long seminarId);
    public Optional<User> findUserById(long seminarId, String userId);

}
