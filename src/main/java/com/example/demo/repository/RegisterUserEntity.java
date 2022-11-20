package com.example.demo.repository;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "REGISTER_USER_ENTITY")
public class RegisterUserEntity {
    @Column(name = "NAME")
    private String name;
    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;
    @Id
    @GeneratedValue
    @Column(name = "S_NO")
    private Long sNo;
    @Column(name = "FLAT_NUMBER")
    private Long flatNumber;
    @Column(name = "PASSWORD")
    private String password;
}
