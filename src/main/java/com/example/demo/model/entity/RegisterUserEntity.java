package com.example.demo.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@Entity
public class RegisterUserEntity {
    private String name;
    private String mobileNumber;
    @Id
    private Long flatNumber;
    private String password;
}
