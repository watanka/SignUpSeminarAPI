package com.signUpSeminar.repository;


import com.signUpSeminar.domain.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class MemoryRegistrationRepositoryTest {

    MemoryRegistrationRepository registrationRepository = new MemoryRegistrationRepository();

    @BeforeEach
    public void setUp(){
        registrationRepository.clearTable();
    }

    @Test
    @DisplayName("특강 수강신청")
    public void register(){
        long seminarId = 1L;
        long userId = 1L;

        User user = new User(1L);

        registrationRepository.save(seminarId, user);

        User userInRepository = registrationRepository.findUserById(seminarId, userId).get();

        assertThat(user).isEqualTo(userInRepository);

    }

//
//    @Test
//    @DisplayName("특강 수강신청 리스트 출력")
//
//    @Test
//    @DisplayName("수강신청 리스트에서 아이디 조회 - 리스트에 포함")
//
//    @Test
//    @DisplayName("수강신청 리스트에서 아이디 조회 - 리스트에 불포함")
}
