package com.nurfaizin.backend.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String username;

    private String email;

    private String password;

    private String phone;

    private String address;

    private String city;

    private String country;

    private String name;

    private String postCode;
}
