package com.nurfaizin.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String todoName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="todo_items",
            joinColumns = @JoinColumn(name = "todo_id"),
            inverseJoinColumns = @JoinColumn(name = "items_id"))
    private Set<Item> items;

    private Boolean isComplete = false;
}
