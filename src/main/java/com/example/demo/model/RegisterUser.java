package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterUser {
    private String number;
    private String name;
    private String mobileNumber;
    private Long flatNumber;
    private String password;
}
