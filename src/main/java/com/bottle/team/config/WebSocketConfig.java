package com.bottle.team.config;

import com.bottle.team.auth.jwt.common.UserContext;
import com.bottle.team.auth.jwt.settings.JwtSettings;
import com.bottle.team.auth.jwt.token.JwtAuthenticationToken;
import com.bottle.team.auth.jwt.token.RawAccessJwtToken;
import com.bottle.team.auth.jwt.token.extractors.JwtHeaderTokenExtractor;
import com.bottle.team.auth.jwt.token.extractors.TokenExtractor;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.SockJsServiceRegistration;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import sun.security.acl.PrincipalImpl;

import java.security.Principal;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
  private TokenExtractor tokenExtractor;
  private JwtSettings jwtSettings;

  @Autowired
  public WebSocketConfig(TokenExtractor tokenExtractor, JwtSettings jwtSettings) {
    this.tokenExtractor = tokenExtractor;
    this.jwtSettings = jwtSettings;
  }

  public static final String TOPIC_PREFIX = "/topic";
  public static final String QUEUE_PREFIX = "/queue";
  public static final String DEFAULT_TOPIC = "/emt";
  public static final String APP_PREFIX = "/app";
  public static final String SOCKET_URL = "/notifications";


  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    // ws sending url prefix (Topic Url) -> When sending data to the client
    config.enableSimpleBroker(TOPIC_PREFIX, QUEUE_PREFIX);
    // listening url prefix (Broker Url) -> When receiving data from the client
    config.setApplicationDestinationPrefixes(APP_PREFIX);

  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    // socket connection url
    registry.addEndpoint(SOCKET_URL).setAllowedOrigins("*");
  }

  @Override
  public void configureClientInboundChannel(ChannelRegistration registration) {
    registration.setInterceptors(new ChannelInterceptorAdapter() {

      @Override
      public Message<?> preSend(Message<?> message, MessageChannel channel) {

        StompHeaderAccessor accessor =
                MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
          String token = accessor.getLogin();
          System.out.println(token);
          Principal user = null;
          if (token == null || token.trim().equals(""))
            user = new PrincipalImpl("guest");
          else {
            RawAccessJwtToken t = new RawAccessJwtToken(tokenExtractor.extract(token));

            Jws<Claims> jwsClaims = t.parseClaims(jwtSettings.getTokenSigningKey());
            String subject = jwsClaims.getBody().getSubject();
            user = new PrincipalImpl(subject);
          }
          accessor.setUser(user);
        }

        return message;
      }
    });
  }

}
