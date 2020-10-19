package com.nurfaizin.backend.service;

import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.model.request.LoginRequest;
import com.nurfaizin.backend.model.request.RegisterRequest;
import com.nurfaizin.backend.model.response.RegisterResponse;

import java.security.NoSuchAlgorithmException;

public interface AuthService {

    RegisterResponse register(RegisterRequest registerRequest) throws NotFoundException;

    RegisterResponse login(LoginRequest request) throws NotFoundException, NoSuchAlgorithmException;

}
