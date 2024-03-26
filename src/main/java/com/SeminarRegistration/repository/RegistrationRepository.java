package com.SeminarRegistration.repository;

import com.SeminarRegistration.domain.AppUser;

import java.util.List;
import java.util.Optional;


public interface RegistrationRepository {

    public String save(String userId);
    public List<String> getAllUsers(long seminarId);
    public String findUserById(String userId);

}
