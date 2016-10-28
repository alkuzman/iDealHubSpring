package com.bottle.team.auth;

import com.bottle.team.config.StaticConstants;
import com.bottle.team.filters.helper.CsrfCookieAddingHelper;
import com.bottle.team.model.authentication.User;
import com.bottle.team.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dimitar on 6/3/2016.
 */
@Component
public class LocalLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        System.out.println("Successful login!");

        String name = authentication.getName();
        User user = userService.findByEmail(name);
        httpServletRequest.getSession().setAttribute("user", user);

        httpServletResponse.addHeader("Access-Control-Allow-Origin", StaticConstants.ANGULAR_APP_URL);
        httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
        CsrfCookieAddingHelper.addingCSRFasCookie(httpServletRequest, httpServletResponse);
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        // super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);

    }
}