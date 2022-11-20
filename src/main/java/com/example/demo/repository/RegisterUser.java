package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterUser extends CrudRepository<RegisterUserEntity, Long> {

    @Query(value= "SELECT TOP(1) NAME, MOBILE_NUMBER, S_NO, FLAT_NUMBER, PASSWORD FROM REGISTER_USER_ENTITY WHERE S_NO=:no",nativeQuery = true)
    Optional<RegisterUserEntity> findBySNo(Long no);

}
