package com.SeminarRegistration.repository;

import com.SeminarRegistration.domain.Registration;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class MemoryRegistrationRepository implements RegistrationRepository{
    private final Map<String, List<Registration>> registrationTable = new ConcurrentHashMap<>();
    ReentrantLock reentrantLock = new ReentrantLock();


    @Override
    public Registration save(String userId, long seminarId) {
        reentrantLock.lock();
        Registration registration = new Registration(userId, seminarId);
        registrationTable.getOrDefault(userId, new ArrayList<>()).add(registration);
        reentrantLock.unlock();

        return registration;
    }
    @Override
    public Optional<Registration> findByUserIdAndSeminarId(String userId, long seminarId) {
        return registrationTable.getOrDefault(userId, new ArrayList<>())
                .stream()
                .filter(r -> r.getSeminarId() == seminarId)
                .findFirst();
    }


    public void clearTable(){
        registrationTable.clear();
    }

}
