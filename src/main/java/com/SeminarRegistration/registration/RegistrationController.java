package com.SeminarRegistration.registration;

import com.SeminarRegistration.domain.AppUser;
import com.SeminarRegistration.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seminar")
public class RegistrationController {

    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/")
    public AppUser signUp(@RequestBody long seminarId, @RequestBody String userId){
        return registrationService.register(seminarId, userId);
    }

    @GetMapping("/{id}")
    public boolean inquire(@RequestBody long seminarId, String userId){

        return registrationService.check(seminarId, userId);
    }
}
