package com.nurfaizin.backend.controller;

import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.model.request.ListRequest;
import com.nurfaizin.backend.model.response.UserResponse;
import com.nurfaizin.backend.model.response.UserResponseList;
import com.nurfaizin.backend.model.response.WebResponse;
import com.nurfaizin.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/api/user/{id}")
    public WebResponse<UserResponse> getUser(@PathVariable Long id) throws NotFoundException {
        UserResponse response = userService.getUser(id);
        return new WebResponse<>(200, "success", response, "");
    }


    @GetMapping(
            value = "/api/user",
            produces = "application/json"
    )
    public WebResponse<List<UserResponse>> list(@RequestParam(value = "size", defaultValue = "10") Integer size,
                                                @RequestParam(value = "page", defaultValue = "0") Integer page) {

        ListRequest request = new ListRequest(page, size);
        List<UserResponse> responses = userService.list(request);
        return new WebResponse<>(200, "success", responses, "");
    }

    @GetMapping(
            value = "/api/tests"
    )
    public ResponseEntity<?> test() {

        return ResponseEntity.ok(new UserResponse(1L, "test", null));
    }


}
