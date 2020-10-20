package com.nurfaizin.backend.service;

import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.model.request.RoleRequest;
import com.nurfaizin.backend.model.response.RoleResponse;

public interface RoleService {

    RoleResponse createRole(RoleRequest request);

    RoleResponse updateRole(Long id, RoleRequest request) throws NotFoundException;

    RoleResponse getRole(Long id) throws NotFoundException;

    void delete(Long id) throws NotFoundException;
}
