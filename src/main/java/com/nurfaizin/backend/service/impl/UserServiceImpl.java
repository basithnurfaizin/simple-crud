package com.nurfaizin.backend.service.impl;

import com.nurfaizin.backend.entity.User;
import com.nurfaizin.backend.repository.UserRepository;
import com.nurfaizin.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    protected UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User findByAuthToken(String token) {
        return repository.findByToken(token);
    }
}
