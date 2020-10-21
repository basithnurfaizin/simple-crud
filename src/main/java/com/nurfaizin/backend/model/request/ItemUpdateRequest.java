package com.nurfaizin.backend.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemUpdateRequest {

    private Long todoId;

    private String itemName;

    private Boolean isCompleted;

}
