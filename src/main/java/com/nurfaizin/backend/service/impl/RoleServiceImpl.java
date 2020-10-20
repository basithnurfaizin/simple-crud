package com.nurfaizin.backend.service.impl;

import com.nurfaizin.backend.entity.Role;
import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.model.request.RoleRequest;
import com.nurfaizin.backend.model.response.RoleResponse;
import com.nurfaizin.backend.repository.RoleRepository;
import com.nurfaizin.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public RoleResponse createRole(RoleRequest request) {
        Role role = new Role();
        role.setName(request.getName());
        repository.save(role);
        return convertModelToResponse(role);
    }

    @Override
    public RoleResponse updateRole(Long id, RoleRequest request) throws NotFoundException {
        Role role = findRoleById(id);
        role.setName(request.getName());
        repository.save(role);
        return convertModelToResponse(role);
    }

    @Override
    public RoleResponse getRole(Long id) throws NotFoundException {
        Role role = findRoleById(id);
        return convertModelToResponse(role);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Role role = findRoleById(id);
        repository.delete(role);
    }

    private RoleResponse convertModelToResponse(Role role) {
        return new RoleResponse(role.getId(), role.getName());
    }

    private Role findRoleById(Long id) throws NotFoundException {
        Optional<Role> role = repository.findById(id);
        if(role.isPresent()) {
            return role.get();
        }
        throw new NotFoundException();
    }
}
