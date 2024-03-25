package com.SeminarRegistration.seminarRegistration.repository;

import com.SeminarRegistration.seminarRegistration.domain.AppUser;

import java.util.List;
import java.util.Optional;


public interface RegistrationRepository {

    public AppUser save(long seminarId, String userId);
    public List<AppUser> getAllUsers(long seminarId);
    public Optional<AppUser> findUserById(long seminarId, String userId);

}
