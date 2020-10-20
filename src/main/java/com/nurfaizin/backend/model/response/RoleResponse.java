package com.nurfaizin.backend.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponse {

    private Long id;

    private String name;

    public RoleResponse(){

    }

    public RoleResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
