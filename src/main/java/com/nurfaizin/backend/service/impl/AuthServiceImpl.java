package com.nurfaizin.backend.service.impl;


import com.nurfaizin.backend.entity.User;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;

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
    public RegisterResponse login(LoginRequest request) throws NoSuchAlgorithmException {
        User user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        if (!(user.getPassword() != null && SecureUtil.getMd5(request.getPassword()).equals(user.getPassword()))) {
            throw new UsernameNotFoundException("User Not Found");
        }

        String token = SecureUtil.generateRandomToken(user.getName() + user.getPassword());

        user.setToken(token);

        repository.save(user);


        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getToken(), null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return convertModelToResponse(user);
    }

    private RegisterResponse convertModelToResponse(User user){
        RegisterResponse response = new RegisterResponse();
        response.setToken(user.getToken());
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        return response;
    }
}
