package com.bottle.team.config;

import com.bottle.team.auth.LocalLoginFailureHandler;
import com.bottle.team.auth.LocalLoginSuccessHandler;
import com.bottle.team.auth.OAuth2TokenService;
import com.bottle.team.auth.OAuthClientResource;
import com.bottle.team.filters.CsrfHeaderFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.filter.CompositeFilter;
import org.springframework.web.filter.RequestContextFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Dimitar on 4/2/2016.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableOAuth2Client
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    OAuth2ClientContext oauth2ClientContext;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception {


        httpSecurity.logout()
                .logoutUrl("/logout");

        httpSecurity.formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/doLogin")
                .successHandler(localLoginSuccessHandler())
                .failureHandler(localLoginFailureHandler());


        httpSecurity.authorizeRequests()
                .antMatchers("/doLogin").permitAll()
                .antMatchers("logout").permitAll()
                .antMatchers("/testGet").permitAll()
                .antMatchers("/data").authenticated();



        httpSecurity.csrf()
              .csrfTokenRepository(csrfTokenRepository());
        // httpSecurity.csrf().disable();

        httpSecurity.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);


        httpSecurity.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
        httpSecurity.addFilterAfter(oauth2AuthenticationFilter(), SessionManagementFilter.class);
        String thiswillberemoved = "tesst";


    }

    @Bean
    LocalLoginSuccessHandler localLoginSuccessHandler() {
        return new LocalLoginSuccessHandler();
    }

    @Bean
    LocalLoginFailureHandler localLoginFailureHandler() {
        return new LocalLoginFailureHandler();
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
