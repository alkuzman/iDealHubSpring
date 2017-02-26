package com.bottle.team.auth.jwt.handlers;

import com.bottle.team.auth.jwt.common.UserContext;
import com.bottle.team.auth.jwt.token.JwtToken;
import com.bottle.team.auth.jwt.token.JwtTokenFactory;
import com.bottle.team.model.enumaration.CertificateType;
import com.bottle.team.service.SecurityProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Viki on 11/16/2016.
 */
@Component
public class JwtAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper mapper;
    private final JwtTokenFactory tokenFactory;
    private final SecurityProfileService securityProfileService;

    @Autowired
    public JwtAwareAuthenticationSuccessHandler(final ObjectMapper mapper, final JwtTokenFactory tokenFactory,
                                                final SecurityProfileService securityProfileService) {
        this.mapper = mapper;
        this.tokenFactory = tokenFactory;
        this.securityProfileService = securityProfileService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        UserContext userContext = (UserContext) authentication.getPrincipal();

        JwtToken accessToken = tokenFactory.createAccessJwtToken(userContext);
        JwtToken refreshToken = tokenFactory.createRefreshToken(userContext);

        /*
        Map<String, String> tokenMap = new HashMap<String, String>();
        tokenMap.put("token", accessToken.getToken());
        tokenMap.put("refreshToken", refreshToken.getToken());
        */

        //TODO: Extract this code in new filter
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", accessToken.getToken());
        map.put("refreshToken", refreshToken.getToken());
        map.put("securityProfileEncryption", securityProfileService
                .findByUserEmailAndCertificateType(userContext.getUsername(), CertificateType.ENCRYPTION));
        map.put("securityProfileSigning", securityProfileService
                .findByUserEmailAndCertificateType(userContext.getUsername(), CertificateType.SIGNING));

        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(httpServletResponse.getWriter(), map);

        clearAuthenticationAttributes(httpServletRequest);
    }

    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
