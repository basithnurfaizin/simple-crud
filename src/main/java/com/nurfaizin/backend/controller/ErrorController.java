package com.nurfaizin.backend.controller;

import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.error.UnauthorizedException;
import com.nurfaizin.backend.model.WebResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = ConstraintViolationException.class)
    public WebResponse<String> validationHandle(ConstraintViolationException constraintViolationException) {
        return  new WebResponse<>(400, "BAD REQUEST", constraintViolationException.getMessage());
    }

    @ExceptionHandler(value = NotFoundException.class)
    public WebResponse<String> notFound(NotFoundException notFoundException){
        return  new WebResponse<>(400, "BAD REQUEST", "Data not Found");
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public WebResponse<String> unauthorized(UnauthorizedException unauthorizedException){
        return  new WebResponse<>(400, "BAD REQUEST", "unauthorized");
    }


}
