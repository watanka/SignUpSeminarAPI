package com.SeminarRegistration.service;
import com.SeminarRegistration.entity.Registration;

import java.util.Optional;

public class RegisterPolicy {

    public void validate(Optional<Registration> registration){
        if (registration.isPresent()){
                throw new IllegalStateException("이미 등록되어있습니다.");
        }
    }
}
