package com.SeminarRegistration.repository;

import com.SeminarRegistration.domain.AppUser;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;

public class MemoryRegistrationRepository implements RegistrationRepository{

//    private final Map<Long, List<AppUser>> registrationTable = new HashMap<>();

    private final Map<Long, List<AppUser>> registrationTable = new ConcurrentHashMap<>();
    ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public AppUser save(long seminarId, String userId) {
        reentrantLock.lock();
        List<AppUser> appUserList = registrationTable.get(seminarId);


        if (appUserList == null) {
            appUserList = new ArrayList<AppUser>();
            registrationTable.put(seminarId, appUserList);
        }

        AppUser appUser = new AppUser(userId);

        appUserList.add(appUser);
        reentrantLock.unlock();
        return appUser;
    }

    @Override
    public List<AppUser> getAllUsers(long seminarId) {
        return registrationTable.getOrDefault(seminarId, new ArrayList<AppUser>());
    }

    @Override
    public Optional<AppUser> findUserById(long seminarId, String userId) {
        return registrationTable.getOrDefault(seminarId, new ArrayList<AppUser>()).stream()
                .filter(user -> user.getUserId().equals(userId))
                .findAny();

    }

    public void clearTable(){
        registrationTable.clear();
    }

}
