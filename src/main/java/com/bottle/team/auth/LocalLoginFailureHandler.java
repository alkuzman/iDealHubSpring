package com.bottle.team.auth;

import com.bottle.team.config.StaticConstants;
import com.bottle.team.filters.helper.CsrfCookieAddingHelper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dimitar on 6/3/2016.
 */
public class LocalLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        System.out.println("Failed login!");
        httpServletResponse.addHeader("Access-Control-Allow-Origin", StaticConstants.ANGULAR_APP_URL);
        httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
        CsrfCookieAddingHelper.addingCSRFasCookie(httpServletRequest, httpServletResponse);
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    }
}
