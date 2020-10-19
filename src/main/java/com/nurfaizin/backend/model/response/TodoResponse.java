package com.nurfaizin.backend.model.response;

import com.nurfaizin.backend.entity.Item;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class TodoResponse {

    private Long id;

    private String name;

    private Set<Item> items;

    public TodoResponse(Long id, String name, Set<Item> items) {
        this.id = id;
        this.name = name;
        this.items = items;
    }
}
