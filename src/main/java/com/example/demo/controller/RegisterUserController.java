package com.example.demo.controller;

import com.example.demo.model.Login;
import com.example.demo.model.RegisterUser;
import com.example.demo.repository.RegisterUserEntity;
import com.example.demo.service.RegisterUserService;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
public class RegisterUserController {

    private final RegisterUserService registerUserService;

    public RegisterUserController(RegisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }

    @PostMapping("/register") //url
    public String registerUser(@RequestBody RegisterUser registerUser) throws AccountNotFoundException {
        return registerUserService.registerUser(registerUser);
    }

    @PostMapping("/") //url
    public String update(@RequestBody RegisterUser registerUser) throws AccountNotFoundException {
        return registerUserService.update(registerUser);
    }

//
//    @PostMapping("/login") //url
//    public String loginUser(@RequestBody Login login) throws AccountNotFoundException {
//        return registerUserService.login(login);
//    }
//
//    @GetMapping("/information/{flatNumber}") //url
//    public RegisterUser getInfo(@PathVariable Long flatNumber) throws AccountNotFoundException {
//        return registerUserService.getInfo(flatNumber);
//    }

    @GetMapping("/security")
    public String testSpringSecurity(){
        return "testing";
    }

    @GetMapping("/users")
    public List<RegisterUserEntity> hibernateCriteria() throws AccountNotFoundException {
        return registerUserService.hibernateCriteria();
    }

    @PutMapping("/update")
    public String hibernateCriteriaUpdate(@RequestBody RegisterUser registerUser) throws AccountNotFoundException {
        return registerUserService.hibernateCriteriaUpdate(registerUser);
    }
}
