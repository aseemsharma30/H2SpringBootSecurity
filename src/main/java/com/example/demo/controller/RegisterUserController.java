package com.example.demo.controller;

import com.example.demo.model.Login;
import com.example.demo.model.RegisterUser;
import com.example.demo.service.RegisterUserService;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

@RestController
public class RegisterUserController {

    private final RegisterUserService registerUserService;

    public RegisterUserController(RegisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }

    @PostMapping("/register") //url
    public String registerUser(@RequestBody RegisterUser registerUser){
        return registerUserService.registerUser(registerUser);
    }

    @PostMapping("/login") //url
    public String loginUser(@RequestBody Login login) throws AccountNotFoundException {
        return registerUserService.login(login);
    }

    @GetMapping("/information/{flatNumber}") //url
    public RegisterUser getInfo(@PathVariable Long flatNumber) throws AccountNotFoundException {
        return registerUserService.getInfo(flatNumber);
    }

    @GetMapping("/security")
    public String testSpringSecurity(){
        return "testing";
    }
}
