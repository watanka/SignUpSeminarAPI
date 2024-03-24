package com.signUpSeminar.repository;

import com.signUpSeminar.domain.User;

import java.util.List;
import java.util.Optional;

public interface RegistrationRepository {

    public User save(long seminarId, User user);
    public List<User> getAllUsers(long seminarId);
    public Optional<User> findUserById(long seminarId, long userId);

}
