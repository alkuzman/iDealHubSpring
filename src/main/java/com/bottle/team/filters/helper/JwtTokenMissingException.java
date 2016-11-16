package com.bottle.team.filters.helper;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by Viki on 11/14/2016.
 */
public class JwtTokenMissingException extends AuthenticationException {
    public JwtTokenMissingException(String s) {
        super(s);
    }
}
