package com.nurfaizin.backend.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class RegisterResponse  implements Serializable {

    private String email;

    private String token;

    private String username;

    public RegisterResponse() {

    }

    public RegisterResponse(String email, String token, String username) {
    }
}
