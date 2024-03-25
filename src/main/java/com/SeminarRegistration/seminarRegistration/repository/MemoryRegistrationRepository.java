package com.SeminarRegistration.seminarRegistration.repository;

import com.SeminarRegistration.seminarRegistration.domain.User;

import java.util.*;

public class MemoryRegistrationRepository implements RegistrationRepository{

    private final Map<Long, List<User>> registrationTable = new HashMap<>();
    private static HashMap<Long, Integer> sequenceBySeminar = new HashMap<Long, Integer>();

    @Override
    public User save(long seminarId, String userId) {
        List<User> userList = registrationTable.get(seminarId);
        int totalNum = sequenceBySeminar.getOrDefault(seminarId, 0);

        if (userList == null){
            userList = new ArrayList<User>();
            registrationTable.put(seminarId, userList);
        }

        User user = new User(totalNum++, userId);

        userList.add(user);
        return user;
    }

    @Override
    public List<User> getAllUsers(long seminarId) {
        return registrationTable.getOrDefault(seminarId, new ArrayList<User>());
    }

    @Override
    public Optional<User> findUserById(long seminarId, String userId) {
        return registrationTable.getOrDefault(seminarId, new ArrayList<User>()).stream()
                .filter(user -> user.userId.equals(userId))
                .findAny();

    }

    public void clearTable(){
        registrationTable.clear();
    }

}
