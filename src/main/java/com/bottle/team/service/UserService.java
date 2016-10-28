package com.bottle.team.service;

import com.bottle.team.model.authentication.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by PC on 09/10/2016.
 */
public interface UserService extends Service<User>, UserDetailsService {
    User findByEmail(String email);
}
