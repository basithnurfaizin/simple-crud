package com.nurfaizin.backend.model.request;


public class ListShoppingRequest {

    private Integer page;

    private  Integer size;

    public ListShoppingRequest(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
