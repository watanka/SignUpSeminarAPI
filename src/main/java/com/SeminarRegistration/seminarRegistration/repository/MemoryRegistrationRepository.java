package com.SeminarRegistration.seminarRegistration.repository;

import com.SeminarRegistration.seminarRegistration.domain.AppUser;

import java.util.*;

public class MemoryRegistrationRepository implements RegistrationRepository{

    private final Map<Long, List<AppUser>> registrationTable = new HashMap<>();

    @Override
    public AppUser save(long seminarId, String userId) {
        List<AppUser> appUserList = registrationTable.get(seminarId);


        if (appUserList == null) {
            appUserList = new ArrayList<AppUser>();
            registrationTable.put(seminarId, appUserList);
        }

        AppUser appUser = new AppUser(userId);

        appUserList.add(appUser);
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
