package com.nurfaizin.backend.model.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ShoppingResponse implements Serializable {

    private Date createdDate;

    private String name;

    private Long id;

    public ShoppingResponse() {

    }

    public ShoppingResponse(Date createdDate, String name, Long id) {

    }
}
