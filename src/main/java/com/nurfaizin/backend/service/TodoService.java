package com.nurfaizin.backend.service;

import com.nurfaizin.backend.entity.Todo;
import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.model.request.ListRequest;
import com.nurfaizin.backend.model.request.TodoRequestCreate;
import com.nurfaizin.backend.model.request.TodoRequestUpdate;
import com.nurfaizin.backend.model.response.TodoResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TodoService {

    TodoResponse createTodo(TodoRequestCreate request);

    TodoResponse get(Long id) throws NotFoundException;

    List<TodoResponse> list(ListRequest request);

    Page<Todo> pageTodo(ListRequest request);

    TodoResponse update(Long id, TodoRequestCreate requestCreate) throws NotFoundException;

    void delete(Long id) throws NotFoundException;

    TodoResponse updateProgress(Long id, TodoRequestUpdate request) throws NotFoundException;

}
