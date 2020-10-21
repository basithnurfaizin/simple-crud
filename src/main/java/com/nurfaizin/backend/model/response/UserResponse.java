package com.nurfaizin.backend.model.response;

import com.nurfaizin.backend.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class UserResponse implements Serializable {

    private Long id;

    private String username;

    private Set<Role> roles;

    public UserResponse(Long id, String username, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;

    }
}
