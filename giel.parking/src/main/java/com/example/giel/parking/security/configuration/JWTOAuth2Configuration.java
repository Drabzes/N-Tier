package com.example.giel.parking.security.configuration;


import com.example.giel.parking.constants.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

@Configuration
@EnableAuthorizationServer
public class JWTOAuth2Configuration extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  @Qualifier("UserDetailsService")
  private UserDetailsService userDetailsService;

  @Autowired
  private DefaultTokenServices tokenServices;

  @Bean
  public static BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
        .withClient(SecurityConfig.CLIENT_ID)
        .secret(passwordEncoder().encode(SecurityConfig.SECRET))
        .authorizedGrantTypes(SecurityConfig.AUTHORIZATION_GRANT_TYPES.toArray(new String[0]))
        .scopes(SecurityConfig.OAUTH2_SCOPES.toArray(new String[0]));

    clients.inMemory()
        .withClient(SecurityConfig.CLIENT_ID)
        .secret(passwordEncoder().encode(SecurityConfig.SECRET))
        .authorizedGrantTypes(SecurityConfig.GRANT_TYPE_PASSWORD)
        .scopes(SecurityConfig.WEB_SCOPE);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    endpoints
        .tokenServices(tokenServices)
        .authenticationManager(authenticationManager)
        .userDetailsService(userDetailsService);
  }

}