package com.nurfaizin.backend.model.request;


import com.nurfaizin.backend.entity.User;
import com.nurfaizin.backend.util.SecureUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.NoSuchAlgorithmException;

@Getter
@Setter
public class RegisterRequest implements EntityRequest<User>{

    private String username;

    private String email;

    private String password;

    private String phone;

    private String address;

    private String city;

    private String country;

    private String name;

    private String postCode;

    @Override
    public User toCreateEntity() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        try {
            if(password==null)
                password ="12345";
            user.setPassword(SecureUtil.getMd5(password));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User toUpdateEntity(User form) {
        return null;
    }
}
