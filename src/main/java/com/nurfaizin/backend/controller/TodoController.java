package com.nurfaizin.backend.controller;

import com.nurfaizin.backend.entity.Todo;
import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.model.request.ListRequest;
import com.nurfaizin.backend.model.request.TodoRequestCreate;
import com.nurfaizin.backend.model.response.TodoResponse;
import com.nurfaizin.backend.model.response.WebResponse;
import com.nurfaizin.backend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;


    @PostMapping(value = "/todo",
            produces = "application/json",
            consumes = "application/json")
    public WebResponse<TodoResponse> createTodo(@RequestBody TodoRequestCreate request) {
        TodoResponse response = todoService.createTodo(request);
        return new WebResponse<>(200, "success", response, "null");
    }


    @GetMapping(value = "/todo/{id}")
    public WebResponse<TodoResponse> getTodo(@PathVariable Long id) throws NotFoundException {
        TodoResponse response = todoService.get(id);
        return  new WebResponse<>(200, "success", response, "null");
    }

    @GetMapping(
            value = "/todo",
            produces = "application/json"
    )
    public WebResponse<List<TodoResponse>> list(@RequestParam(value = "size", defaultValue = "10") Integer size,
                                                @RequestParam(value = "page", defaultValue = "0") Integer page) {

        ListRequest request = new ListRequest(page, size);
        List<TodoResponse> todoResponseList = todoService.list(request);
        return new WebResponse<>(200, "success", todoResponseList, "null");

    }


    @GetMapping(
            value = "/todopage",
            produces = "application/json"
    )
    public WebResponse<Page<Todo>> listPage(@RequestParam(value = "size", defaultValue = "10") Integer size,
                                            @RequestParam(value = "page", defaultValue = "0") Integer page) {

        ListRequest request = new ListRequest(page, size);
        Page<Todo> todoPage = todoService.pageTodo(request);
        return new WebResponse<>(200, "success", todoPage, "null");

    }

}
