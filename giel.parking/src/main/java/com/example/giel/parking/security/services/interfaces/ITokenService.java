package com.example.giel.parking.security.services.interfaces;

import com.example.giel.parking.security.models.CurrentUser;
import com.example.giel.parking.security.models.JWTAuthenticationRequest;
import javax.servlet.http.HttpServletRequest;

public interface ITokenService {

  CurrentUser getUserToken(JWTAuthenticationRequest authenticationRequest);

  CurrentUser refreshJWToken(HttpServletRequest request);

}
