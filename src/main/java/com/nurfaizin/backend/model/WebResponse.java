package com.nurfaizin.backend.model;

import java.io.Serializable;


public class WebResponse<T> implements Serializable {
    public Integer code;

    public String status;

    public T data;

    public WebResponse(Integer code, String status, T data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }
}
