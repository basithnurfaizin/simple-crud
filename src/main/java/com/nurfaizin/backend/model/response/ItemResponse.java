package com.nurfaizin.backend.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemResponse {

    private Long id;

    private String itemName;

    private Boolean isCompleted;

    public ItemResponse(){

    }

    public ItemResponse(Long id, String itemName, Boolean isCompleted) {
        this.id = id;
        this.itemName = itemName;
        this.isCompleted = isCompleted;
    }
}
