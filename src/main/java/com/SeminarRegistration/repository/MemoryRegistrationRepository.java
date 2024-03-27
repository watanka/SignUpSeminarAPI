package com.SeminarRegistration.repository;

import com.SeminarRegistration.domain.Registration;
import com.SeminarRegistration.domain.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class MemoryRegistrationRepository implements RegistrationRepository{

//    private final Map<Long, List<AppUser>> registrationTable = new HashMap<>();

    private final Map<Long, List<User>> registrationTable = new ConcurrentHashMap<>();
    ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public Registration save(long seminarId, String userId) {
        reentrantLock.lock();
        List<User> userList = registrationTable.getOrDefault(seminarId, new ArrayList<>());
        Registration registration =  getRegistration(seminarId, userId, userList);

        if (userList.isEmpty()) {
            registrationTable.put(seminarId, userList);
        }
        reentrantLock.unlock();

        return registration;
    }



    @Override
    public List<User> getAllUsers(long seminarId) {
        return registrationTable.getOrDefault(seminarId, new ArrayList<>());
    }

    @Override
    public Optional<User> findUserById(long seminarId, String userId) {
        return registrationTable.getOrDefault(seminarId, new ArrayList<User>()).stream()
                .filter(user -> user.getUserId().equals(userId))
                .findAny();

    }

    public void clearTable(){
        registrationTable.clear();
    }

    private static Registration getRegistration(long seminarId, String userId, List<User> userList) {
        User user = null;
        for (User u: userList){
            if (u.getUserId().equals(userId)){
                user = u;
                break;
            }
        }
        if (user != null){
            return new Registration(seminarId, userId);
        }else{
            return null;
        }
    }

}
