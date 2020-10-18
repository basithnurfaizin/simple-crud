package com.nurfaizin.backend.service.impl;


import com.nurfaizin.backend.entity.User;
import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.model.request.LoginRequest;
import com.nurfaizin.backend.repository.UserRepository;
import com.nurfaizin.backend.service.AuthService;
import com.nurfaizin.backend.model.request.RegisterRequest;
import com.nurfaizin.backend.model.response.RegisterResponse;
import com.nurfaizin.backend.util.SecureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sun.plugin.liveconnect.SecurityContextHelper;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    protected UserRepository repository;


    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        User user = registerRequest.toCreateEntity();
        String token = SecureUtil.generateRandomToken(user.getName() + user.getPassword());
        user.setToken(token);
        repository.save(user);
        return convertModelToResponse(user);
    }

    @Override
    public RegisterResponse login(LoginRequest request) throws NotFoundException, NoSuchAlgorithmException {
        Optional<User> user = repository.findByUsername(request.getUsername());
        if(!user.isPresent()) {
            throw new NotFoundException();
        }
        if (!(user.get().getPassword() != null && SecureUtil.getMd5(request.getPassword()).equals(user.get().getPassword()))) {
            throw new NotFoundException();
        }

        String token = SecureUtil.generateRandomToken(user.get().getName() + user.get().getPassword());

        user.get().setToken(token);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.get(), user.get().getToken(), null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return convertModelToResponse(user.get());
    }

    private RegisterResponse convertModelToResponse(User user){
        return new RegisterResponse(
                user.getEmail(),
                user.getToken(),
                user.getUsername());
    }
}
