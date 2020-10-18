package com.nurfaizin.backend.controller.conf;

import com.nurfaizin.backend.entity.User;
import com.nurfaizin.backend.error.UnauthorizedException;
import com.nurfaizin.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(token == null || token.isEmpty()) {
            throw new UnauthorizedException("Token not found. please login first");
        }

        User user = userService.findByAuthToken(token);
        if(user == null ) {
            throw new UnauthorizedException("Your token is invalid. Please login again");
        }
        request.setAttribute("current_user", user);
        return true;
    }
}
