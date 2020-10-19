package com.nurfaizin.backend.service.impl;


import com.nurfaizin.backend.entity.User;
import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.model.request.LoginRequest;
import com.nurfaizin.backend.repository.UserRepository;
import com.nurfaizin.backend.security.jwt.JwtUtils;
import com.nurfaizin.backend.service.AuthService;
import com.nurfaizin.backend.model.request.RegisterRequest;
import com.nurfaizin.backend.model.response.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    protected UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) throws NotFoundException {

        if(repository.existsByUsername(registerRequest.getUsername())) {
            throw new NotFoundException();
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setName(registerRequest.getName());
        repository.save(user);
        return convertModelToResponse(user);
    }

    @Override
    public RegisterResponse login(LoginRequest request) throws NoSuchAlgorithmException {



        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        System.out.println(jwt);
        return new RegisterResponse(userDetails.getEmail(), jwt, userDetails.getUsername());
    }

    private RegisterResponse convertModelToResponse(User user){
        RegisterResponse response = new RegisterResponse();
        response.setToken(user.getToken());
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        return response;
    }
}
