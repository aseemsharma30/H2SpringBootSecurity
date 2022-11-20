package com.example.demo.service;

import com.example.demo.model.Login;
import com.example.demo.model.RegisterUser;
import com.example.demo.repository.RegisterUserEntity;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterUserService {

    private final com.example.demo.repository.RegisterUser registerUserRepository;






    public RegisterUserService(com.example.demo.repository.RegisterUser registerUserRepository) {
        this.registerUserRepository = registerUserRepository;
    }


    public String registerUser(RegisterUser registerUser) throws AccountNotFoundException {
        RegisterUserEntity registerUserEntity = new RegisterUserEntity();

        if(registerUser.getName() != null){
            registerUserEntity.setName(registerUser.getName());
        }
        if(registerUser.getMobileNumber() != null){
            registerUserEntity.setMobileNumber(registerUser.getMobileNumber());
        }
        if(registerUser.getPassword() != null){
            registerUserEntity.setPassword(registerUser.getPassword());
        }
        if(registerUser.getFlatNumber() != null){
            registerUserEntity.setFlatNumber(registerUser.getFlatNumber());
        }

        registerUserRepository.save(registerUserEntity);
        return "updated";
        }

        public String update(RegisterUser registerUser) throws AccountNotFoundException {
            RegisterUserEntity registerUserEntity = new RegisterUserEntity();
            registerUserEntity = registerUserRepository.findBySNo(Long.parseLong(registerUser.getNumber()))
                    .orElseThrow(() -> new AccountNotFoundException("flat number not found"));
            if(registerUser.getName() != null){
                registerUserEntity.setName(registerUser.getName());
            }
            if(registerUser.getMobileNumber() != null){
                registerUserEntity.setMobileNumber(registerUser.getMobileNumber());
            }
            if(registerUser.getPassword() != null){
                registerUserEntity.setPassword(registerUser.getPassword());
            }
            if(registerUser.getFlatNumber() != null){
                registerUserEntity.setFlatNumber(registerUser.getFlatNumber());
            }

            registerUserRepository.save(registerUserEntity);
            return "updated";
        }
//
//    public String login(Login login) throws AccountNotFoundException {
//        RegisterUserEntity registerUserEntity = registerUserRepository.findById(login.getFlatNumber())
//                .orElseThrow(() -> new AccountNotFoundException("Flat Number not registered"));
//
//        if(registerUserEntity.getPassword().equals(login.getPassword())){
//            return "Login Success";
//        }else {
//            return "Please enter correct password";
//        }
//    }
//
//    public RegisterUser getInfo(Long flatNumber) throws AccountNotFoundException {
//
//        RegisterUserEntity registerUserEntity = registerUserRepository.findById(flatNumber)
//                .orElseThrow(() -> new AccountNotFoundException("Flat Number not registered"));
//
//        RegisterUser registerUser = new RegisterUser();
//        registerUser.setName(registerUserEntity.getName());
//        registerUser.setFlatNumber(registerUserEntity.getFlatNumber());
//        registerUser.setMobileNumber(registerUserEntity.getMobileNumber());
//
//        return registerUser;
//    }

    public List<RegisterUserEntity> hibernateCriteria() throws AccountNotFoundException {
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        Criteria criteria = session.createCriteria(RegisterUserEntity.class);
        List<RegisterUserEntity> usersList = criteria.list();
        session.close();
        return usersList;
    }

    public String hibernateCriteriaUpdate(RegisterUser registerUser) throws AccountNotFoundException {
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        Criteria criteria = session.createCriteria(RegisterUserEntity.class);
        List<RegisterUserEntity> usersList = criteria.list();

        Long number = Long.parseLong(registerUser.getNumber());
        Transaction tx = session.beginTransaction();
        RegisterUserEntity registerUserEntity = usersList.stream().filter(user -> user.getSNo().equals(number))
                .findFirst().orElseThrow(() -> new AccountNotFoundException("Flat Number not found"));

        if(registerUser.getName() != null){
            registerUserEntity.setName(registerUser.getName());
        }
        if(registerUser.getMobileNumber() != null){
            registerUserEntity.setMobileNumber(registerUser.getMobileNumber());
        }
        if(registerUser.getPassword() != null){
            registerUserEntity.setPassword(registerUser.getPassword());
        }
        if(registerUser.getFlatNumber() != null){
            registerUserEntity.setFlatNumber(registerUser.getFlatNumber());
        }
        session.saveOrUpdate(registerUserEntity);
        tx.commit();

        session.close();
        return "updated successfully";
    }

}
