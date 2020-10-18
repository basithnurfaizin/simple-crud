package com.nurfaizin.backend.model.request;

public interface EntityRequest<T> {

    T toCreateEntity();

    T toUpdateEntity(T form);
}
