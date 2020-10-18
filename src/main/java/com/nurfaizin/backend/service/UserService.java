package com.nurfaizin.backend.service;

import com.nurfaizin.backend.entity.User;

public interface UserService {

    User save(User user);

    User findByAuthToken(String token);
}
