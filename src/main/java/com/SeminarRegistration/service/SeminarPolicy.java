package com.SeminarRegistration.service;

import com.SeminarRegistration.entity.Seminar;

public class SeminarPolicy{
    void validate(Seminar seminar){

        long currentRegistrationCount = seminar.getCurrentRegistrationCount();
        long maxRegistrationNum = seminar.getMaxRegistrationNum();

        if (currentRegistrationCount >= maxRegistrationNum){
            throw new RuntimeException("정원이 초과되어 등록할 수 없습니다.");
        }
    }
}
