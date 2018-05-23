package com.example.giel.parking.controllers;


import static com.example.giel.parking.constants.EndPoints.GENERATE_JWT_TOKEN;
import static com.example.giel.parking.constants.EndPoints.REFRESH_JWT_TOKEN;
import static com.example.giel.parking.constants.EndPoints.TOKEN_PREFIX;
import static com.example.giel.parking.constants.SecurityConstants.AUTHORIZATION_HEADER;
import static com.example.giel.parking.constants.SecurityConstants.TOKEN_BEARER_PREFIX;
import static com.example.giel.parking.utils.ResponseUtil.okResponse;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;


import com.example.giel.parking.security.models.CurrentUser;
import com.example.giel.parking.security.models.JWTAuthenticationRequest;
import com.example.giel.parking.security.services.interfaces.ITokenService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = TOKEN_PREFIX, produces = APPLICATION_JSON_UTF8_VALUE)
public class JwtAuthController {

  @Autowired
  private ITokenService tokenService;

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(value = GENERATE_JWT_TOKEN)
  public ResponseEntity createAuthenticationToken(
      @RequestBody JWTAuthenticationRequest jwtAuthenticationRequest,
      HttpServletRequest request,
      HttpServletResponse response) {

    CurrentUser currentUser = tokenService.getUserToken(jwtAuthenticationRequest);

    response.addHeader(AUTHORIZATION_HEADER,
        String.format("%s %s", TOKEN_BEARER_PREFIX, currentUser.getJwtToken()));

    return okResponse(currentUser);
  }
  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(value = REFRESH_JWT_TOKEN)
  public ResponseEntity refreshJWToken(HttpServletRequest request) {
    return okResponse(tokenService.refreshJWToken(request));
  }

}
