package com.SeminarRegistration;

import com.SeminarRegistration.domain.RegisterPolicy;
import com.SeminarRegistration.repository.JpaRegistrationRepository;
import com.SeminarRegistration.repository.RegistrationRepository;


import com.SeminarRegistration.service.RegisterPolicyImpl;
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
    public RegistrationRepository registrationRepository(){
        //TODO: policy 주입하기
        return new JpaRegistrationRepository(em);
    }

    @Bean
    public RegisterPolicy registerPolicy(){
        return new RegisterPolicyImpl();
    }

    @Bean
    public RegistrationService registrationService(){
        return new RegistrationService(registrationRepository());
    };

    @Bean
    public RegistrationController registrationController(){
        return new RegistrationController(registrationService());
    }



}
