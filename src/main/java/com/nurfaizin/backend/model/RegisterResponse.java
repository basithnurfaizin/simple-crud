package com.nurfaizin.backend.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RegisterResponse  implements Serializable {

    private String email;

    private String token;

    private String username;
}
