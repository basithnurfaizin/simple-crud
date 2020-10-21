package com.nurfaizin.backend.model.response;

import com.nurfaizin.backend.entity.Role;

import java.io.Serializable;
import java.util.List;

public class UserResponseList implements Serializable {

    private List<UserResponse> users;

    private int page;

    private int pageOf;

    public UserResponseList(List<UserResponse> users, int page, int pageOf) {
        this.users = users;
        this.page = page;
        this.pageOf = pageOf;
    }
}
