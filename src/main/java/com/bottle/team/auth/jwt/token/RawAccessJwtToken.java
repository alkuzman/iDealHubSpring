package com.bottle.team.auth.jwt.token;

/**
 * Created by Viki on 11/16/2016.
 */

import com.bottle.team.auth.jwt.exceptions.JwtExpiredTokenException;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.BadCredentialsException;

public class RawAccessJwtToken implements JwtToken {

    private String token;

    public RawAccessJwtToken(String token) {
        this.token = token;
    }

    public Jws<Claims> parseClaims(String signingKey) {
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token);
        } catch (UnsupportedJwtException ex){
            throw new BadCredentialsException("Invalid JWT token: ", ex);
        } catch(MalformedJwtException ex) {
            throw new BadCredentialsException("Invalid JWT token: ", ex);
        } catch (IllegalArgumentException ex) {
            throw new BadCredentialsException("Invalid JWT token: ", ex);
        } catch (SignatureException ex){
            throw new BadCredentialsException("Invalid JWT token: ", ex);
        } catch (ExpiredJwtException expiredEx) {
            throw new JwtExpiredTokenException(this, "JWT Token expired", expiredEx);
        }
    }

    @Override
    public String getToken() {
        return token;
    }
}