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

    @PostMapping("/registerNensi") // this is for th:action
    public String processRegistration(RegistrationForm registerForm){
        // in register.html we have id but it also workds with th:object
        // to specify an object to which the submitted form data will be bound
        jpaUserRepository.save(registerForm.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
