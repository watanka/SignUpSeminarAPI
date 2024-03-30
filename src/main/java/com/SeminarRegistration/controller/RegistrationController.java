package com.SeminarRegistration.controller;

import com.SeminarRegistration.controller.dto.request.CheckRegistrationRequest;
import com.SeminarRegistration.controller.dto.request.RegisterRequest;
import com.SeminarRegistration.controller.dto.response.CheckRegistrationResponse;
import com.SeminarRegistration.controller.dto.SeminarDTO;
import com.SeminarRegistration.service.RegistrationService;
import com.SeminarRegistration.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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

    @PostMapping("/{seminarId}")
    public void register(@PathVariable long seminarId, @RequestBody RegisterRequest request){


        try{
            registrationService.register(request.getUserId(), seminarId);

        }catch (Exception e){// 동시성 에러시
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        seminarService.updateRegistrationCount(seminarId);

    }

    @GetMapping("/{seminarId}")
    public CheckRegistrationResponse checkRegistration(@PathVariable long seminarId, @PathVariable CheckRegistrationRequest request){
        boolean registerResult = registrationService.checkRegistration( request.getUserId(), seminarId);

        return new CheckRegistrationResponse(request.getUserId(), seminarId, registerResult);
    }

    @GetMapping("/")
    public List<SeminarDTO> getSeminarList(){
        return seminarService.getAllSeminars();
    }

}