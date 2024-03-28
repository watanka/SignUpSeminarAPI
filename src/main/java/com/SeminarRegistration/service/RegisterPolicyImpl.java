package com.SeminarRegistration.service;

import com.SeminarRegistration.domain.RegisterPolicy;
import com.SeminarRegistration.domain.User;

import java.util.List;
import java.util.Optional;

public class RegisterPolicyImpl implements RegisterPolicy {


    @Override
    public boolean validate(String userId, long currentEnrollmentCount, long maxEnrollment, Optional<User> registerList) throws IllegalStateException{

        if (!checkRegisterListFull(maxEnrollment, currentEnrollmentCount){
            throw new IllegalStateException("이미 등록되어있습니다.");
        }
        if (!checkAlreadySignedUp(userId, registerList)){
            throw new IllegalStateException("등록이 마감되었습니다.");
        }
        return true;
    }


    public boolean checkAlreadySignedUp(String userId, Optional<User> registerList){
        return registerList.stream()
                .anyMatch(user -> user.getUserId().equals(userId));
    }

    public boolean checkRegisterListFull(long maxEnrollment, long currentEnrollmentCount){

        return currentEnrollmentCount >= maxEnrollment;

    }

}
