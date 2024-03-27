package com.SeminarRegistration;

import com.SeminarRegistration.repository.JpaRegistrationRepository;
import com.SeminarRegistration.repository.RegistrationRepository;


import com.SeminarRegistration.service.RegistrationService;
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
    public RegistrationRepository RegistrationRepository(){
        return new JpaRegistrationRepository(em);
    }

    @Bean
    public RegistrationService registrationService(){
        return new RegistrationService(RegistrationRepository());
    };

    @Bean
    public RegistrationController registrationController(){
        return new RegistrationController(registrationService());
    }



}
