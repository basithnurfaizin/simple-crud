package com.nurfaizin.backend.model;

import java.io.Serializable;
import java.util.Date;


public class ShoppingResponse implements Serializable {

    private Date createdDate;

    private String name;

    private Long id;

    public ShoppingResponse() {

    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShoppingResponse(Date createdDate, String name, Long id) {
        this.createdDate = createdDate;
        this.name = name;
        this.id = id;
    }
}
