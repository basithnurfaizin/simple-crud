package com.nurfaizin.backend.service;

import com.nurfaizin.backend.model.RegisterRequest;
import com.nurfaizin.backend.model.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest registerRequest);

}
