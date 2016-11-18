package com.bottle.team.auth.jwt.token.verifiers;

/**
 * Created by Viki on 11/17/2016.
 */
public interface TokenVerifier {
    public boolean verify(String jti);
}
