package com.nurfaizin.backend.model.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RegisterResponse  implements Serializable {

    private String email;

    private String token;

    private String username;

    public RegisterResponse(String email, String token, String username) {
    }
}
