package com.nurfaizin.backend.controller;

import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.model.request.LoginRequest;
import com.nurfaizin.backend.model.request.RegisterRequest;
import com.nurfaizin.backend.model.response.RegisterResponse;
import com.nurfaizin.backend.model.response.WebResponse;
import com.nurfaizin.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    protected AuthService service;

    @PostMapping(value = "/register",
            produces = "application/json",
            consumes = "application/json")
    public WebResponse<RegisterResponse> register(@RequestBody RegisterRequest request) throws NotFoundException {
        RegisterResponse response = service.register(request);
        return  new WebResponse<>(200, "success", response, "");
    }


    @PostMapping(value = "/login",
            produces = "application/json",
            consumes = "application/json")
    public WebResponse<RegisterResponse> login(@RequestBody LoginRequest request) throws NotFoundException, NoSuchAlgorithmException {
        RegisterResponse response = service.login(request);
        return  new WebResponse<>(200, "success", response, "");
    }
}
