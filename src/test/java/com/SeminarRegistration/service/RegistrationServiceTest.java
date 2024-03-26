package com.SeminarRegistration.service;

import com.SeminarRegistration.registration.RegistrationService;
import com.SeminarRegistration.seminarRegistration.domain.AppUser;
import com.SeminarRegistration.repository.MemoryRegistrationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class RegistrationServiceTest {

    MemoryRegistrationRepository memoryRepository = new MemoryRegistrationRepository();
    RegistrationService registrationService;

    long seminarId = 1L;
    String userId = "eunsung";

    @BeforeEach
    void setUp(){
        memoryRepository.clearTable();
        registrationService = new RegistrationService(memoryRepository);
    }

    @Test
    @DisplayName("세미나 등록")
    public void registerSuccess(){

        AppUser appUser = registrationService.register(seminarId, userId);

        assertThat(registrationService.check(seminarId, userId)).isEqualTo(true);

    }

    @Test
    @DisplayName("세미나 등록실패 - 이미 등록")
    public void registerFailAlreadyRegistered(){
        registrationService.register(seminarId, userId);

        try{
            registrationService.register(seminarId, userId); // same id
            fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 등록되어있습니다.");
        }
    }

    @Test
    @DisplayName("세미나 등록실패 - 등록 인원 30명 초과")
    public void registerFailFullRegistration(){

        for (int i=0; i < 30; i++) {
            String userName = "user" + i;
            registrationService.register(seminarId, userName);
        }


        try{
            registrationService.register(seminarId, userId); // same id
            fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("등록이 마감되었습니다.");

        }
    }


//    @Test
//    @DisplayName("세미나 등록체크 - Id등록되어있지않음")
//    public void registerCheckNotSignedUp(){
//
//
//        assertThat(registrationService.check(seminarId, userId)).isEqualTo(false);
//    }
//

    //    @Test
    //    @DisplayName("[이미 등록]세미나 신청 실패")
    //
    //    @Test
    //    @DisplayName("[신청인원 초과마감]세미나 신청 실패")
    //
    //    @Test
    //    @DisplayName("[수강신청 기간X]세미나 신청 실패")
//}

}
