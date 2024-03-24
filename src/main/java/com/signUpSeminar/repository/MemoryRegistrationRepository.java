package com.signUpSeminar.repository;

import com.signUpSeminar.domain.User;

import java.util.*;

public class MemoryRegistrationRepository implements RegistrationRepository{

    private final Map<Long, List<User>> registrationTable = new HashMap<>();


    @Override
    public User save(long seminarId, User user) {
        List<User> userList = registrationTable.get(seminarId);

        if (userList == null){
            userList = new ArrayList<User>();
            registrationTable.put(seminarId, userList);
        }

        userList.add(user);
        return user;
    }

    @Override
    public List<User> getAllUsers(long seminarId) {
        return registrationTable.get(seminarId);
    }

    @Override
    public Optional<User> findUserById(long seminarId, long userId) {
        List<User> userList = registrationTable.get(seminarId);
        return userList.stream()
                .filter(user -> user.userId() ==userId)
                .findAny();

    }

    public void clearTable(){
        registrationTable.clear();
    }

}
