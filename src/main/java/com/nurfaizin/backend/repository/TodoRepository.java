package com.nurfaizin.backend.repository;

import com.nurfaizin.backend.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Todo findByTodoName(String name);
}
