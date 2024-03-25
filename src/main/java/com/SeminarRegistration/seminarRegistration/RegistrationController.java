package com.SeminarRegistration.seminarRegistration;

import com.SeminarRegistration.seminarRegistration.domain.AppUser;
import com.SeminarRegistration.seminarRegistration.service.RegistrationService;
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
