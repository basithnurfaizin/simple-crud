package com.nurfaizin.backend.service;

import com.nurfaizin.backend.entity.User;
import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.model.response.UserResponse;

public interface UserService {

    User save(User user);

    User findByAuthToken(String token);

    UserResponse getUser(Long id) throws NotFoundException;
}
