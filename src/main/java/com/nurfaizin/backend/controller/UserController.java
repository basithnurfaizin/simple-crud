package com.nurfaizin.backend.controller;

import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.model.response.UserResponse;
import com.nurfaizin.backend.model.response.WebResponse;
import com.nurfaizin.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/api/user/{id}")
    public WebResponse<UserResponse> getUser(@PathVariable Long id) throws NotFoundException {
        UserResponse response = userService.getUser(id);
        return new WebResponse<>(200, "success", response, "");
    }

}
