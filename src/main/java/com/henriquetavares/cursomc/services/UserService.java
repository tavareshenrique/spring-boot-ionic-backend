package com.henriquetavares.cursomc.services;

import com.henriquetavares.cursomc.security.UserSpringSecurity;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static UserSpringSecurity authenticated() {
        try {
            return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }

    }

}
