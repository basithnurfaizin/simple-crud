package com.nurfaizin.backend.model.response;

import java.io.Serializable;


public class WebResponse<T> implements Serializable {

    public Integer code;

    public String status;

    public T data;

    public int page;

    public int pageOf;

    public WebResponse(Integer code, String status, T data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }

    public WebResponse(Integer code, String status, T data, int page, int pageOf) {
        this.code = code;
        this.status = status;
        this.data = data;
        this.page = page;
        this.pageOf = pageOf;
    }
}
