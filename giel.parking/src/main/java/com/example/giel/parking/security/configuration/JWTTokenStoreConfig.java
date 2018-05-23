package com.example.giel.parking.security.configuration;

import com.example.giel.parking.constants.SecurityConfig;
import com.example.giel.parking.constants.SecurityConstants;
import com.example.giel.parking.security.JWTTokenEnhancer;
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
public class JWTTokenStoreConfig {

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(jwtAccessTokenConverter());
  }

  @Bean
  @Primary
  public DefaultTokenServices defaultTokenServices() {
    DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
    defaultTokenServices.setTokenStore(tokenStore());
    defaultTokenServices.setSupportRefreshToken(true);
    defaultTokenServices.setAccessTokenValiditySeconds(SecurityConfig.ACCESS_TOKEN_VALIDITY);
    defaultTokenServices.setRefreshTokenValiditySeconds(SecurityConstants.REFRESH_TOKEN_1_DAY);

    TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
    tokenEnhancerChain
        .setTokenEnhancers(Arrays.asList(jwtTokenEnhancer(), jwtAccessTokenConverter()));

    defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);

    return defaultTokenServices;
  }

  @Bean
  public JwtAccessTokenConverter jwtAccessTokenConverter() {
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(
        SecurityConfig.KEYSTORE_NAME), SecurityConfig.KEYSTORE_PASSWORD.toCharArray());
    converter.setKeyPair(keyStoreKeyFactory.getKeyPair(SecurityConfig.KEYSTORE_SIGNATURE_ALIAS));
    return converter;
  }

  @Bean
  public TokenEnhancer jwtTokenEnhancer() {
    return new JWTTokenEnhancer();
  }

}