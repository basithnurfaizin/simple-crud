package com.nurfaizin.backend.model.response;

import java.io.Serializable;


public class WebResponse<T> implements Serializable {

    public Integer code;

    public String message;

    public T data;

    public String errorMessage;

    public WebResponse(Integer code, String status, T data, String errorMessage) {
        this.code = code;
        this.message = status;
        this.data = data;
        this.errorMessage = errorMessage;
    }

}
