package com.signUpSeminar.service;

import com.signUpSeminar.domain.User;
import com.signUpSeminar.repository.MemoryRegistrationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class RegistrationServiceTest {

    MemoryRegistrationRepository memoryRepository = new MemoryRegistrationRepository();
    RegistrationService registrationService = new RegistrationService(memoryRepository);

    @Test
    @DisplayName("세미나 등록")
    public void registerSuccess(){
        long seminarId = 1L;
        long userId = 1L;

        User user = registrationService.register(seminarId, userId);


        assertThat(registrationService.check(seminarId, userId)).isEqualTo(true);

    }

    @Test
    @DisplayName("세미나 등록실패")
    public void registerFail(){
        long seminarId = 1L;
        long userId = 1L;
        registrationService.register(seminarId, userId);

        try{
            registrationService.register(seminarId, userId); // same id
            fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 등록되어있습니다.");

        }
    }

    @Test
    @DisplayName("세미나 등록체크 - Id등록되어있지않음")
    public void registerCheckNotSignedUp(){
        long seminarId = 1L;
        long userId = 1L;
        assertThat(registrationService.check(seminarId, userId)).isEqualTo(false);
    }

}
