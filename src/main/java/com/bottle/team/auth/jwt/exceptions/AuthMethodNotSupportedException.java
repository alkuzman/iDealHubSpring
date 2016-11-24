package com.bottle.team.auth.jwt.exceptions;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * Created by Viki on 11/15/2016.
 */
public class AuthMethodNotSupportedException extends AuthenticationServiceException {
    public AuthMethodNotSupportedException(String s) {
        super(s);
    }
}