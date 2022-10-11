package com.taco.cloud.controller;

import com.taco.cloud.model.RegistrationForm;
import com.taco.cloud.repo.jpa.JpaUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("register")
public class RegisterUserController {

    private JpaUserRepository jpaUserRepository;
    private PasswordEncoder passwordEncoder;

    public RegisterUserController(JpaUserRepository jpaUserRepository, PasswordEncoder passwordEncoder) {
        this.jpaUserRepository = jpaUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public  String registrationForm(){
        return "register";
    }

    @PostMapping
    public String processRegistration(RegistrationForm registrationForm){
        jpaUserRepository.save(registrationForm.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
