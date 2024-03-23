package com.signUpSeminar;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seminar")
public class SignUpController {
    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    private SignUpService signUpService;
    @PostMapping("/")
    public String signUp(@RequestBody long userId){
        return signUpService.register(userId);
    }

    @GetMapping("/{id}")
    public String inquire(long userId){
        return signUpService.checkSignUp(userId);
    }
}
