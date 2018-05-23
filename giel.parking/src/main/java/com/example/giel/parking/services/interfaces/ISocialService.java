package com.example.giel.parking.services.interfaces;

public interface ISocialService {

  String createSocialLoginAuthorizationURL();

  String createSocialLoginAccessToken(String code);

  String getUserEmail(String accessToken);

}
