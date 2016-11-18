package com.bottle.team.config;

import com.bottle.team.auth.jwt.antMatchers.SkipPathRequestMatcher;
import com.bottle.team.auth.jwt.entryPoints.RestAuthenticationEntryPoint;
import com.bottle.team.auth.jwt.providers.JwtAuthenticationProvider;
import com.bottle.team.auth.jwt.providers.JwtLoginProvider;
import com.bottle.team.auth.jwt.token.extractors.TokenExtractor;
import com.bottle.team.auth.oAuth2.OAuth2TokenService;
import com.bottle.team.auth.oAuth2.OAuthClientResource;
import com.bottle.team.filters.JwtLoginProcessingFilter;
import com.bottle.team.filters.JwtTokenAuthenticationProcessingFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.CompositeFilter;
import org.springframework.web.filter.RequestContextFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Dimitar on 4/2/2016.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableOAuth2Client
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
    public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/auth/login";
    public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/ideas";
    public static final String TOKEN_REFRESH_ENTRY_POINT = "/auth/token";


    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    OAuth2ClientContext oauth2ClientContext;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private AuthenticationFailureHandler failureHandler;
    @Autowired
    private JwtLoginProvider jwtLoginProvider;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private TokenExtractor tokenExtractor;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(this.restAuthenticationEntryPoint)

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers(FORM_BASED_LOGIN_ENTRY_POINT).permitAll()
                .antMatchers(TOKEN_REFRESH_ENTRY_POINT).permitAll()

                .and()
                .authorizeRequests()
                .antMatchers(TOKEN_BASED_AUTH_ENTRY_POINT).authenticated()

                .and()
                .addFilterBefore(buildJwtLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);

        //httpSecurity.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
        //httpSecurity.addFilterAfter(oauth2AuthenticationFilter(), SessionManagementFilter.class);


    }

    @Bean
    protected Filter buildJwtTokenAuthenticationProcessingFilter() {
        List<String> pathsToSkip = Arrays.asList(TOKEN_REFRESH_ENTRY_POINT, FORM_BASED_LOGIN_ENTRY_POINT);
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, TOKEN_BASED_AUTH_ENTRY_POINT);
        JwtTokenAuthenticationProcessingFilter filter
                = new JwtTokenAuthenticationProcessingFilter(failureHandler, tokenExtractor, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    @Bean
    protected Filter buildJwtLoginProcessingFilter() {
        JwtLoginProcessingFilter filter = new JwtLoginProcessingFilter(FORM_BASED_LOGIN_ENTRY_POINT, successHandler, failureHandler, objectMapper);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(jwtLoginProvider);
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    private Filter oauth2AuthenticationFilter() throws Exception {
        OAuth2AuthenticationProcessingFilter filter = new OAuth2AuthenticationProcessingFilter();
        filter.setStateless(false);
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }


    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    @Bean
    public FilterRegistrationBean requestContextFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RequestContextFilter());
        registration.setOrder(-105);
        return registration;
    }


    private Filter ssoFilter() {

        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<Filter>();

        /*filters.add(
                getOauthFilter(
                        "/login/facebook",
                        facebook(),
                        facebookSuccessHandler()
                )
        );

        filters.add(
                getOauthFilter(
                        "/login/linked",
                        linked(),
                        linkedInSuccessHandler()
                )
        );*/


        filter.setFilters(filters);
        return filter;

    }

    /*@Bean
    SocialNetworkLoginSuccessHandler facebookSuccessHandler() {
        return new SocialNetworkLoginSuccessHandler(Provider.FACEBOOK, Role.ROLE_USER, facebookService);
    }

    @Bean
    @ConfigurationProperties("facebook")
    OAuthClientResource facebook() {
        return new OAuthClientResource();
    }

    @Bean
    SocialNetworkLoginSuccessHandler linkedInSuccessHandler() {
        return new SocialNetworkLoginSuccessHandler(Provider.LINKED, Role.ROLE_USER, linkedInService);
    }

    @Bean
    @ConfigurationProperties("linked")
    OAuthClientResource linked() {
        return new OAuthClientResource();
    }*/

    public Filter getOauthFilter(
            String loginUrl,
            OAuthClientResource client,
            AuthenticationSuccessHandler successHandler) {
        OAuth2ClientAuthenticationProcessingFilter oauthFilter =
                new OAuth2ClientAuthenticationProcessingFilter(loginUrl);
        OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
        oauthFilter.setRestTemplate(template);
        oauthFilter.setTokenServices(
                new OAuth2TokenService(
                        client.getResource().getUserInfoUri(),
                        client.getClient().getClientId()
                ));
        oauthFilter.setAuthenticationSuccessHandler(successHandler);
        return oauthFilter;
    }

    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

}
