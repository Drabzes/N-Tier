package com.example.giel.parking.security;

import com.example.giel.parking.constants.TokenProperty;
import com.example.giel.parking.repositories.dao.UserCredentialsRepository;
import com.example.giel.parking.services.implementations.UserInfoServiceImpl;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

@Component
public class JWTTokenEnhancer implements TokenEnhancer {

  @Autowired
  private UserInfoServiceImpl userInfoClient;

  @Autowired
  private UserCredentialsRepository userCredentialsRepository;

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
      OAuth2Authentication authentication) {

    Map<String, Object> additionalInfo = new HashMap<>();

    String email = ((User) authentication.getPrincipal()).getUsername();

    additionalInfo.put(TokenProperty.UUID, getCurrentUserUuid(email));

    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

    return accessToken;
  }

  private String getCurrentUserUuid(String email) {
    return userInfoClient.getUuidByUserInfoId(getCurrentUserInfoIdByEmail(email));
  }

  private Long getCurrentUserInfoIdByEmail(String email) {
    return userCredentialsRepository.findByEmail(email).getUserInfoId();
  }

}