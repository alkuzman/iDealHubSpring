package com.bottle.team.auth.jwt.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.security.jwt")
public class JwtSettings {

    private Integer tokenExpirationTime;

    /**
     * Token issuer.
     */
    private String tokenIssuer;


    private String tokenSigningKey;


    private Integer refreshTokenExpTime;


    private Integer refreshTokenExpTimeRememberMe;

    public Integer getRefreshTokenExpTimeRememberMe() {
        return refreshTokenExpTimeRememberMe;
    }

    public void setRefreshTokenExpTimeRememberMe(Integer refreshTokenExpTimeRememberMe) {
        this.refreshTokenExpTimeRememberMe = refreshTokenExpTimeRememberMe;
    }

    public Integer getRefreshTokenExpTime() {
        return refreshTokenExpTime;
    }

    public void setRefreshTokenExpTime(Integer refreshTokenExpTime) {
        this.refreshTokenExpTime = refreshTokenExpTime;
    }

    public Integer getTokenExpirationTime() {
        return tokenExpirationTime;
    }

    public void setTokenExpirationTime(Integer tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
    }

    public String getTokenIssuer() {
        return tokenIssuer;
    }

    public void setTokenIssuer(String tokenIssuer) {
        this.tokenIssuer = tokenIssuer;
    }

    public String getTokenSigningKey() {
        return tokenSigningKey;
    }

    public void setTokenSigningKey(String tokenSigningKey) {
        this.tokenSigningKey = tokenSigningKey;
    }
}