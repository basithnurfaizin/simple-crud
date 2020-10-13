package com.nurfaizin.backend.service.impl;

import com.nurfaizin.backend.repository.UserRepository;
import com.nurfaizin.backend.service.AuthService;
import com.nurfaizin.backend.model.RegisterRequest;
import com.nurfaizin.backend.model.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    protected UserRepository repository;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {

        return null;
    }
}
