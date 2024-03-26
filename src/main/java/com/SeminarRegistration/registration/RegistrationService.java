package com.SeminarRegistration.registration;

import com.SeminarRegistration.domain.AppUser;
import com.SeminarRegistration.repository.RegistrationRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    UserList userList;

    @Autowired
    public RegistrationService(UserList userList ) {
        this.userList = userList;
    }

    public AppUser register(String userId) {
        if (userList.findById(userId).isPresent()){
            throw new IllegalStateException("이미 등록되어있습니다.");
        }
        if (userList.getAll().size() >= 30){
            throw new IllegalStateException("등록이 마감되었습니다.");
        }
        userList.add(userId);

        return appUser;
    }

    public boolean check(long seminarId, String userId) {
        return registrationRepository.findUserById(seminarId, userId).isPresent();
    }
}
