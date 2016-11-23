package com.bottle.team.auth.jwt.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by Viki on 11/15/2016.
 */
public class UserContext {
    private final String username;
    private final Boolean rememberMe;
    private final Collection<? extends GrantedAuthority> authorities;

    private UserContext(String username, Boolean rememberMe, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.authorities = authorities;
        this.rememberMe = rememberMe;
    }

    public static UserContext create(String username, Boolean rememberMe, Collection<? extends GrantedAuthority> authorities) {
        if (StringUtils.isBlank(username)) throw new IllegalArgumentException("Username is blank: " + username);
        return new UserContext(username, rememberMe, authorities);
    }

    public String getUsername() {
        return username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }
}