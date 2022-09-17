package com.example.demo.service;

import com.example.demo.model.Login;
import com.example.demo.model.RegisterUser;
import com.example.demo.model.entity.RegisterUserEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Collection;
import java.util.Map;

@Service
public class RegisterUserService {

    private final com.example.demo.repository.RegisterUser registerUserRepository;

    public RegisterUserService(com.example.demo.repository.RegisterUser registerUserRepository) {
        this.registerUserRepository = registerUserRepository;
    }


    public String registerUser(RegisterUser registerUser) {
        RegisterUserEntity registerUserEntity = new RegisterUserEntity();
        registerUserEntity.setFlatNumber(registerUser.getFlatNumber());
        registerUserEntity.setName(registerUser.getName());
        registerUserRepository.save(registerUserEntity);
        return "User Registered";
    }

    public String login(Login login) throws AccountNotFoundException {
        RegisterUserEntity registerUserEntity = registerUserRepository.findById(login.getFlatNumber())
                .orElseThrow(() -> new AccountNotFoundException("Flat Number not registered"));

        if(registerUserEntity.getPassword().equals(login.getPassword())){
            return "Login Success";
        }else {
            return "Please enter correct password";
        }
    }

    public RegisterUser getInfo(Long flatNumber) throws AccountNotFoundException {

        RegisterUserEntity registerUserEntity = registerUserRepository.findById(flatNumber)
                .orElseThrow(() -> new AccountNotFoundException("Flat Number not registered"));

        RegisterUser registerUser = new RegisterUser();
        registerUser.setName(registerUserEntity.getName());
        registerUser.setFlatNumber(registerUserEntity.getFlatNumber());
        registerUser.setMobileNumber(registerUserEntity.getMobileNumber());

        return registerUser;
    }

}
