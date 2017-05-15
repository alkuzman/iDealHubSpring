package com.bottle.team.service;

import com.bottle.team.model.authentication.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by PC on 09/10/2016.
 */
public interface UserService extends Service<User>, UserDetailsService {
    User findByEmailWithNoPassword(String email);

    User findByEmail(String email);

    boolean isEmailTaken(String email);

    User activate(String code);

    User resendActivationCode(String email);
}
