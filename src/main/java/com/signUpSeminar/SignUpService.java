package com.signUpSeminar;

import org.springframework.stereotype.Service;

@Service
public class SignUpService {



    public String register(long userId) {

        return "요청 성공";
    }

    public String checkSignUp(long userId) {

        return "등록되었습니다.";
    }
}
