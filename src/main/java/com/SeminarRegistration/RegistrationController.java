package com.SeminarRegistration;

import com.SeminarRegistration.domain.Registration;
import com.SeminarRegistration.service.RegistrationService;
import com.SeminarRegistration.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seminar")
public class RegistrationController {

    private SeminarService seminarService;
    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(SeminarService seminarService, RegistrationService registrationService) {
        this.seminarService = seminarService;
        this.registrationService = registrationService;
    }

    @PostMapping("{seminarId}/{userId}")
    public void register(@PathVariable long seminarId, @PathVariable String userId){
        registrationService.register(userId, seminarId);
        seminarService.updateRegistrationCount(seminarId);

    }

    @GetMapping("{seminarId}/{id}")
    public boolean checkRegistration(@PathVariable long seminarId, @PathVariable String userId){
        return registrationService.checkRegistration(seminarId, userId);
    }
}