package com.nurfaizin.backend.service.impl;


import com.nurfaizin.backend.entity.Role;
import com.nurfaizin.backend.entity.User;
import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.model.request.LoginRequest;
import com.nurfaizin.backend.repository.RoleRepository;
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
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    protected UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

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
        Set<Role> roles = new HashSet<>();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setName(registerRequest.getName());
        for (Long id : registerRequest.getRoleIds()) {
            Role role = roleRepository.getOne(id);
            roles.add(role);
        }
        user.setRoles(roles);
        repository.save(user);

        Authentication authentication = auth(registerRequest.getUsername(), registerRequest.getPassword());
        String jwt = generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new RegisterResponse(userDetails.getEmail(), jwt, userDetails.getUsername());
    }

    @Override
    public RegisterResponse login(LoginRequest request) throws NoSuchAlgorithmException {

        Authentication authentication = auth(request.getUsername(), request.getPassword());
        String jwt = generateToken(authentication);

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

    private Authentication auth(String username , String password) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
    }

    private String generateToken(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtUtils.generateJwtToken(authentication);
    }
}
