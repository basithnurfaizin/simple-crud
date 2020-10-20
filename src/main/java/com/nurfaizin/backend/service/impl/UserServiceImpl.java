package com.nurfaizin.backend.service.impl;

import com.nurfaizin.backend.entity.User;
import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.model.response.UserResponse;
import com.nurfaizin.backend.repository.UserRepository;
import com.nurfaizin.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public UserResponse getUser(Long id) throws NotFoundException {
        User user = findUserById(id);
        return new UserResponse(user.getId(), user.getUsername(), user.getRoles());
    }

    private User findUserById(Long id) throws NotFoundException {
        Optional<User> user = repository.findById(id);
        if(!user.isPresent()) {
            throw new NotFoundException();
        }
        return user.get();
    }
}
