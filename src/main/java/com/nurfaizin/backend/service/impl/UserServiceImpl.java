package com.nurfaizin.backend.service.impl;

import com.nurfaizin.backend.entity.User;
import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.model.request.ListRequest;
import com.nurfaizin.backend.model.response.UserResponse;
import com.nurfaizin.backend.repository.UserRepository;
import com.nurfaizin.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<UserResponse> list(ListRequest request) {
        Page<User> page = repository.findAll(PageRequest.of(request.getPage(), request.getSize()));
        List<User> userList = page.get().collect(Collectors.toList());
        List<UserResponse> responses = new ArrayList<>();
        for (User user : userList) {
            UserResponse userResponse = new UserResponse(user.getId(), user.getUsername(), user.getRoles());
            responses.add(userResponse);
        }
        return responses;
    }

    private User findUserById(Long id) throws NotFoundException {
        Optional<User> user = repository.findById(id);
        if(!user.isPresent()) {
            throw new NotFoundException();
        }
        return user.get();
    }
}
