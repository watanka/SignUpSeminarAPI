package com.SeminarRegistration;

import com.SeminarRegistration.service.RegisterPolicy;
import com.SeminarRegistration.service.RegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class RegistrationServiceTest {


    MemoryRegistrationRepository memoryRepository = new MemoryRegistrationRepository();
    RegisterPolicy registerPolicy = new RegisterPolicy();
    RegistrationService registrationService = new RegistrationService(memoryRepository, registerPolicy);

    long seminarId = 1L;
    String userId = "eunsung";

    @BeforeEach
    void setUp() {
        memoryRepository.clearTable();
        registrationService = new RegistrationService(memoryRepository, registerPolicy);
    }

    @Test
    @DisplayName("세미나 등록")
    public void registerSuccess() {

        registrationService.register(userId, seminarId);

        assertThat(registrationService.checkRegistration(userId, seminarId)).isEqualTo(true);

    }

    @Test
    @DisplayName("세미나 등록실패 - 이미 등록")
    public void registerFailAlreadyRegistered() {
        registrationService.register(userId, seminarId);

        try {
            registrationService.register(userId, seminarId); // same id
            fail();
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo("이미 등록되어있습니다.");
        }
    }

    @Test
    @DisplayName("세미나 등록실패 - 등록 인원 30명 초과")
    public void registerFailFullRegistration() {

        for (int i = 0; i < 30; i++) {
            String userName = "user" + i;
            registrationService.register(userName, seminarId);
        }


        try {
            registrationService.register(userId, seminarId); // same id
            fail();
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo("정원이 초과되어 등록할 수 없습니다.");
        }
    }

//    @DisplayName("동시에 신청을 했을 때, 순서를 보장한다.")
//    @RepeatedTest(100)
//    void concurrencyTest(){
//        //given:
//        int maxThreads = 30;
//        ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);
//
//        AtomicInteger index = new AtomicInteger(0);
//
//        for (int i = 0; i < maxThreads; i++){
//            executorService.execute(() ->{
//                registrationService.register("user" + index, seminarId);
//                index.getAndIncrement();
//            });
//        }
//
//        executorService.shutdown();
//        try{
//            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        List<String> userNameListInOrder = new ArrayList<>();
//        for (int i=0; i < 30; i++){
//            userNameListInOrder.add("user"+i);
//        }
//
//        List<String> userListInConcurrency = registrationRepository.getAllUsers(seminarId).stream()
//                .map(user -> user.getUserId())
//                .collect(Collectors.toList());
//
//        assertThat(userListInConcurrency).isEqualTo(userNameListInOrder);
//
//    }


}
