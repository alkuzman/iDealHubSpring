package com.bottle.team.validation;

import com.bottle.team.auth.jwt.common.UserContext;
import com.bottle.team.model.authentication.User;
import com.bottle.team.model.enumaration.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by AKuzmanoski on 23/11/2016.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 23/11/2016
 */
@Component
public class AuthenticatedUserValidation implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            errors.reject("notAuthenticated", "you have to authenticate in order to access this resource");
        else {
            UserContext userContext = (UserContext) authentication.getPrincipal();
            if (!userContext.getUsername().equals(user.getEmail()))
                errors.reject("username.invalid", "you have no privilege to represent other users");
            userContext.getAuthorities().stream().filter(authority -> !Role.valueOf(authority.getAuthority()).equals(user.getRole())).forEach(authority -> errors.reject("role.invalid", "you have no privilege to change your roles"));
        }
    }
}
