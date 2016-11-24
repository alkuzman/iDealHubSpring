package com.bottle.team.auth.jwt.antMatchers;

import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Viki on 11/17/2016.
 */
public class SkipPathRequestMatcher implements RequestMatcher {

    private final OrRequestMatcher matchers;
    private final OrRequestMatcher processingMatcher;

    public SkipPathRequestMatcher(List<String> pathsToSkip, List<String> processingPaths) {
        Assert.notNull(pathsToSkip);
        List<RequestMatcher> s = pathsToSkip.stream().map(path -> new AntPathRequestMatcher(path)).collect(Collectors.toList());
        List<RequestMatcher> m = processingPaths.stream().map(path -> new AntPathRequestMatcher(path, HttpMethod.POST.toString())).collect(Collectors.toList());
        matchers = new OrRequestMatcher(s);
        processingMatcher = new OrRequestMatcher(m);
    }

    @Override
    public boolean matches(HttpServletRequest httpServletRequest) {
        if (matchers.matches(httpServletRequest)) {
            return false;
        }
        return processingMatcher.matches(httpServletRequest) ? true : false;
    }
}
