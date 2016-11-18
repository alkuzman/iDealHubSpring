package com.bottle.team.auth.jwt.token.extractors;

/**
 * Created by Viki on 11/16/2016.
 */
public interface TokenExtractor {
    public String extract(String payload);
}
