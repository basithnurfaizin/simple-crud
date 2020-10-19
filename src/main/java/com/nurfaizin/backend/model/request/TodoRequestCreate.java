package com.nurfaizin.backend.model.request;

import com.nurfaizin.backend.entity.Item;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class TodoRequestCreate {

    private String name;

    private List<String> items;
}
