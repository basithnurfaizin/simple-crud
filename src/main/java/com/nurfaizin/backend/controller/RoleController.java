package com.nurfaizin.backend.controller;

import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.model.request.RoleRequest;
import com.nurfaizin.backend.model.response.RoleResponse;
import com.nurfaizin.backend.model.response.WebResponse;
import com.nurfaizin.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Secured("ROLE_ADMIN")
    @PostMapping(value = "/api/role",
            produces = "application/json",
            consumes = "application/json")
    public WebResponse<RoleResponse> create(@RequestBody RoleRequest request) {
        RoleResponse response = roleService.createRole(request);
        return new WebResponse<>(200,"success", response, "");
    }


    @Secured("ROLE_ADMIN")
    @PutMapping(value = "/api/role/{id}",
            produces = "application/json",
            consumes = "application/json")
    public WebResponse<RoleResponse> update(@RequestBody RoleRequest request,@PathVariable Long id) throws NotFoundException {
        RoleResponse response = roleService.updateRole(id, request);
        return new WebResponse<>(200,"success", response, "");
    }

    @GetMapping(value = "api/role/{id}")
    public WebResponse<RoleResponse> get(@PathVariable Long id) throws NotFoundException {
        RoleResponse response = roleService.getRole(id);
        return new WebResponse<>(200, "success", response, "");
    }


    @Secured("ROLE_ADMIN")
    @DeleteMapping(value = "/api/role/{id}")
    public WebResponse<String> delete(@PathVariable Long id) throws NotFoundException {
        roleService.delete(id);
        return new WebResponse<>(200, "success", "deleted", "");
    }



}
