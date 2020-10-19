package com.nurfaizin.backend.service.impl;

import com.nurfaizin.backend.entity.Item;
import com.nurfaizin.backend.entity.Todo;
import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.model.request.ListRequest;
import com.nurfaizin.backend.model.request.TodoRequestCreate;
import com.nurfaizin.backend.model.response.TodoResponse;
import com.nurfaizin.backend.repository.ItemRepository;
import com.nurfaizin.backend.repository.TodoRepository;
import com.nurfaizin.backend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository repository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public TodoResponse createTodo(TodoRequestCreate request) {
        Set<Item> items = new HashSet<>();

        for(String i : request.getItems()) {
            Item item = new Item();
            item.setItemName(i);
            itemRepository.save(item);
            items.add(item);
        }
        Todo todo = new Todo();
        todo.setItems(items);
        todo.setTodoName(request.getName());
        repository.save(todo);
        return convertModelToResponse(todo);
    }

    @Override
    public TodoResponse get(Long id) throws NotFoundException {
        Todo todo = findTodoById(id);

        return convertModelToResponse(todo);
    }

    @Override
    public List<TodoResponse> list(ListRequest request) {
        Page<Todo> page = repository.findAll(PageRequest.of(request.getPage(), request.getSize()));
        List<Todo> todoList = page.get().collect(Collectors.toList());
        List<TodoResponse> todoResponses = new ArrayList<>();
        for (Todo todo : todoList) {
            TodoResponse response = convertModelToResponse(todo);
            todoResponses.add(response);
        }
        return todoResponses;
    }

    @Override
    public Page<Todo> pageTodo(ListRequest request) {
        return repository.findAll(PageRequest.of(request.getPage(), request.getSize()));
    }

    @Override
    public TodoResponse update(Long id, TodoRequestCreate requestCreate) throws NotFoundException {
        Todo todo = findTodoById(id);
        Set<Item> items = new HashSet<>();
        todo.setTodoName(requestCreate.getName());
        for (String s : requestCreate.getItems()) {
            Item item = itemRepository.findByItemName(s);
            items.add(item);
        }
        todo.setItems(items);
        repository.save(todo);
        return convertModelToResponse(todo);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Todo todo = findTodoById(id);
        repository.delete(todo);
    }


    private TodoResponse convertModelToResponse(Todo todo) {
        return new TodoResponse(todo.getId(), todo.getTodoName(), todo.getItems());
    }

    private Todo findTodoById(Long id) throws NotFoundException {
        Optional<Todo> todo = repository.findById(id);
        if(todo.isPresent()) {
            throw new NotFoundException();
        }
        return  todo.get();
    }
}
