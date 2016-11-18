package com.bottle.team.auth.jwt.token.verifiers;

import org.springframework.stereotype.Component;

/**
 * Created by Viki on 11/17/2016.
 */
@Component
public class BloomFilterTokenVerifier implements TokenVerifier {
    @Override
    public boolean verify(String jti) {
        return true;
    }
}