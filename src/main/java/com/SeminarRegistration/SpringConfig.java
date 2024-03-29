package com.SeminarRegistration;

import com.SeminarRegistration.domain.Seminar;
import com.SeminarRegistration.repository.*;


import com.SeminarRegistration.service.RegisterPolicy;
import com.SeminarRegistration.service.RegistrationService;
import com.SeminarRegistration.service.SeminarPolicy;
import com.SeminarRegistration.service.SeminarService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class SpringConfig {


    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public RegistrationRepository registrationRepository(){
        return new JpaRegistrationRepository(em);
    }

    @Bean
    public RegisterPolicy registerPolicy(){
        return new RegisterPolicy();
    }

    @Bean
    public RegistrationService registrationService(){
        return new RegistrationService(registrationRepository(), registerPolicy());
    };

    @Bean
    public SeminarRepository seminarRepository(){
        return new MemorySeminarRepository();
    }

    @Bean
    public SeminarPolicy seminarPolicy(){
        return new SeminarPolicy();
    }
    @Bean
    public SeminarService seminarService(){
        return new SeminarService(seminarRepository(), seminarPolicy());
    }


    @Bean
    public RegistrationController registrationController(){
        return new RegistrationController(seminarService(), registrationService());
    }



}
