package com.bottle.team.service;

import com.bottle.team.model.authentication.User;
import com.bottle.team.web.exceptions.ActivationCodeIsWrongException;
import com.bottle.team.web.exceptions.UserActivatedException;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by PC on 09/10/2016.
 */
public interface UserService extends Service<User>, UserDetailsService {
    User findByEmailWithNoPassword(String email);

    User findByEmail(String email);

    boolean isEmailTaken(String email);

    User activate(String code, String email) throws UserActivatedException, ActivationCodeIsWrongException;

    User resendActivationCode(String email);
}
