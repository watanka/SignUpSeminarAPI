package com.SeminarRegistration.repository;


import com.SeminarRegistration.domain.Registration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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

        registrationRepository.save(userId, seminarId);

        Optional<Registration> registration = registrationRepository.findByUserIdAndSeminarId(userId, seminarId);

        assertThat(registration.isPresent());

    }

//
    @Test
    @DisplayName("특강 수강신청 리스트 출력")
    public void getRegisterList(){
        for (int i=0;i<30;i++){
            String userId = "user"+i;
            registrationRepository.save(userId, seminarId);
        }


        assertThat(registrationRepository.findBySeminarId(seminarId).size()).isEqualTo(30);
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

        Optional<User> foundUser = registrationRepository.findUserById(seminarId, userId);

        assertThat(foundUser).isEqualTo(Optional.empty());
    }

    @Test
    @DisplayName("동시에 신청을 했을 때, 순서를 보장한다.")
    @RepeatedTest(100)
    void concurrencyTest(){
        //given:
        int maxThreads = 30;
        ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);

        AtomicInteger index = new AtomicInteger(0);

        for (int i = 0; i < maxThreads; i++){
            executorService.execute(() ->{
                    registrationRepository.save(seminarId, "user" + index);
                    index.getAndIncrement();
            });
        }

        executorService.shutdown();
        try{
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<String> userNameListInOrder = new ArrayList<>();
        for (int i=0; i < 30; i++){
            userNameListInOrder.add("user"+i);
        }

        List<String> userListInConcurrency = registrationRepository.getAllUsers(seminarId).stream()
                        .map(user -> user.getUserId())
                                .collect(Collectors.toList());

        assertThat(userListInConcurrency).isEqualTo(userNameListInOrder);


    }



}
