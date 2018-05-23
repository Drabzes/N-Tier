package com.example.giel.parking.controllers;

import static com.example.giel.parking.constants.EndPoints.FACEBOOK_CALLBACK;
import static com.example.giel.parking.constants.EndPoints.GOOGLE_CALLBACK;
import static com.example.giel.parking.constants.EndPoints.REGISTER_USER;
import static com.example.giel.parking.constants.EndPoints.SIGN_IN_PROVIDERS;
import static com.example.giel.parking.constants.ResponseMessage.EMPTY_EMAIL;
import static com.example.giel.parking.constants.ServiceQualifiers.FACEBOOK_SERVICE;
import static com.example.giel.parking.utils.ResponseUtil.badResponse;
import static com.example.giel.parking.utils.ResponseUtil.createdResponse;
import static java.util.Objects.isNull;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import com.example.giel.parking.models.UserCredential;
import com.example.giel.parking.services.interfaces.IAuthenticationService;
import com.example.giel.parking.services.interfaces.ISocialService;

import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
public class AuthenticationController {



  @Autowired
  private IAuthenticationService authenticationService;

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(value = REGISTER_USER, produces = APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity registerUser(@RequestBody UserCredential userCredentialsToRegister) {
    return createdResponse(authenticationService.registerUser(userCredentialsToRegister));
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping(value = SIGN_IN_PROVIDERS, produces = APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity providerSignIn(@PathVariable String providerId) throws URISyntaxException {
    URI socialProviderLocation = null;

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setLocation(socialProviderLocation);

    return new ResponseEntity(httpHeaders, HttpStatus.SEE_OTHER);
  }

//  @GetMapping(value = GOOGLE_CALLBACK, produces = APPLICATION_JSON_UTF8_VALUE)
//  public ResponseEntity googleCallback(@RequestParam String code) {
//    String accessToken = googleService.createSocialLoginAccessToken(code);
//    String email = googleService.getUserEmail(accessToken);
//
//    return authenticationService.socialLogin(email);
//  }


//  @GetMapping(value = FACEBOOK_CALLBACK, produces = APPLICATION_JSON_UTF8_VALUE)
//  public ResponseEntity facebookCallback(@RequestParam String code) {
//    String accessToken = facebookServiceImpl.createSocialLoginAccessToken(code);
//    String email = facebookServiceImpl.getUserEmail(accessToken);
//
//    if (isNull(email)) {
//      return badResponse(EMPTY_EMAIL);
//    }
//
//    return authenticationService.socialLogin(email);
//  }

}
