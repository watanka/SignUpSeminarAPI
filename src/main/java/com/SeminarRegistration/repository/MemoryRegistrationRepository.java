package com.SeminarRegistration.repository;

import com.SeminarRegistration.domain.Registration;
import com.SeminarRegistration.domain.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class MemoryRegistrationRepository implements RegistrationRepository{

//    private final Map<Long, List<AppUser>> registrationTable = new HashMap<>();

    //TODO: maxEnrollmentNum을 조회할 리포지토리를 정해야함.
    public final long maxEnrollmentNum = 30;
    private final Map<Long, List<User>> registrationTable = new ConcurrentHashMap<>();
    private final Map<Long, Integer> currentEnrollmentCountMap = new ConcurrentHashMap<>();
    ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public Registration save(long seminarId, String userId) {
        reentrantLock.lock();
        Registration registration = new Registration(seminarId, userId);

        registrationTable.getOrDefault(seminarId, new ArrayList<>()).add(new User(userId));
        currentEnrollmentCountMap.put(seminarId,
                                    (currentEnrollmentCountMap.getOrDefault(seminarId, 0) + 1));
        reentrantLock.unlock();

        return registration;
    }



    @Override
    public List<User> getAllUsers(long seminarId) {
        return registrationTable.getOrDefault(seminarId, new ArrayList<>());
    }

    @Override
    public Optional<User> findUserById(long seminarId, String userId) {
        return registrationTable.getOrDefault(seminarId, new ArrayList<>()).stream()
                .filter(user -> user.getUserId().equals(userId))
                .findAny();

    }

    @Override
    public long getCurrentEnrollmentCount(long seminarId) {
        return currentEnrollmentCountMap.getOrDefault(seminarId, 0);
    }


    @Override
    public long getMaxEnrollmentNum(){
        return maxEnrollmentNum;
    }

    public void clearTable(){
        registrationTable.clear();
    }


}
