package com.SeminarRegistration.seminarRegistration.repository;


import com.SeminarRegistration.seminarRegistration.domain.AppUser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class MemoryRegistrationRepositoryTest {

    MemoryRegistrationRepository registrationRepository = new MemoryRegistrationRepository();


    long seminarId = 1L;
    String userId = "eunsung";


    @BeforeEach
    public void setUp(){
        registrationRepository.clearTable();
    }

    @Test
    @DisplayName("특강 수강신청")
    public void register(){

        registrationRepository.save(seminarId, userId);

        AppUser appUserInRepository = registrationRepository.findUserById(seminarId, userId).get();

        assertThat(appUserInRepository.getUserId()).isEqualTo(userId);

    }

//
    @Test
    @DisplayName("특강 수강신청 리스트 출력")
    public void getRegisterList(){
        for (int i=0;i<30;i++){
            String userId = "user"+i;
            registrationRepository.save(seminarId, userId);
        }


        assertThat(registrationRepository.getAllUsers(seminarId).size()).isEqualTo(30);
    }

    @Test
    @DisplayName("수강신청 리스트에서 아이디 조회 - 리스트에 포함")
    public void checkRegistered(){
        //given
        registrationRepository.save(seminarId, userId);

        String foundUserName = registrationRepository.findUserById(seminarId, userId).get().getUserId();

        assertThat(foundUserName).isEqualTo(userId);
    }
    @Test
    @DisplayName("수강신청 리스트에서 아이디 조회 - 리스트에 불포함")
    public void checkNotRegistered(){

        Optional<AppUser> foundUser = registrationRepository.findUserById(seminarId, userId);

        assertThat(foundUser).isEqualTo(Optional.empty());
    }


}
