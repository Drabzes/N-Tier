package com.example.giel.parking.services.implementations;


import static com.example.giel.parking.constants.ResponseMessage.EMAIL_ALREADY_IN_USE;
import static com.example.giel.parking.constants.Roles.DEFAULT_ROLE;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.logging.log4j.util.Strings.isBlank;

import com.example.giel.parking.constants.SecurityConfig;
import com.example.giel.parking.constants.SocialScopes;
import com.example.giel.parking.models.Role;
import com.example.giel.parking.models.UserCredential;
import com.example.giel.parking.repositories.dao.RolesRepository;
import com.example.giel.parking.repositories.dao.UserCredentialsRepository;
import com.example.giel.parking.services.interfaces.IAuthenticationService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

  @Autowired
  private UserInfoServiceImpl userInfoClient;

  @Autowired
  private UserCredentialsRepository userCredentialsRepository;

  @Autowired
  private RolesRepository rolesRepository;

  @Autowired
  private DefaultTokenServices tokenServices;

  @Value("${frontend.callback}")
  private String frontendCallback;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public UserCredential registerUser(UserCredential userCredentialsToRegister) {

    String email = userCredentialsToRegister.getEmail();

    email = email.toLowerCase();

    String password = userCredentialsToRegister.getPassword();


    UserCredential userCredential = userCredentialsRepository.findByEmail(email);

    Long userInfoId = userInfoClient.createNewEmptyUser();

    Role userRole = rolesRepository.findByName(DEFAULT_ROLE);

    String encryptedPassword = bCryptPasswordEncoder.encode(password);

    try {
      userCredential = userCredentialsRepository.saveAndFlush(
          new UserCredential(email, encryptedPassword, new HashSet<>(Arrays.asList(userRole)),
              userInfoId));
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityViolationException(EMAIL_ALREADY_IN_USE);
    }

    return userCredential;
  }

  @Override
  public ResponseEntity socialLogin(String email) {
    if (isNull(userCredentialsRepository.findByEmail(email))) {
      createUserCredentialsFromSocialLogin(email);
    }

    OAuth2AccessToken token = getCustomToken(email);

    URI socialProviderLocation = null;

    try {
      socialProviderLocation = new URI(
          frontendCallback + "?access_token=" + token.getValue() + "&refresh_token="
              + token.getRefreshToken().getValue());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setLocation(socialProviderLocation);

    return new ResponseEntity(httpHeaders, HttpStatus.SEE_OTHER);
  }

  private void createUserCredentialsFromSocialLogin(String email) {

    long userInfoId = userInfoClient.createNewEmptyUser();

    Role userRole = rolesRepository.findByName(DEFAULT_ROLE);

    try {
      userCredentialsRepository.saveAndFlush(
          new UserCredential(email, null, new HashSet<>(Arrays.asList(userRole)), userInfoId));
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityViolationException(e.getMessage());
    }
  }

  /**
   * Generate a token for a given user without checking his credentials (it has already be validated
   * by Facebook, Google, or an other provider)
   *
   * @param email The user's email address
   * @return An OAuth2AccessToken similar to what you get with local authentication by
   * email/password
   */
  private OAuth2AccessToken getCustomToken(String email) {
    UserCredential userCredential = userCredentialsRepository.findByEmail(email);

    HashMap<String, String> authorizationParameters = new HashMap();
    authorizationParameters.put("username", userCredential
        .getEmail()); //TODO keep? seems useless but was in the code I copy pasted. The code still work if this is removed but maybe there is something hidden

    Set<GrantedAuthority> authorities = new HashSet();
    userCredential.getRoles()
        .forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

    Set<String> responseType = new HashSet(); //TODO keep? seems useless but was in the code I copy pasted. The code still work if this is removed but maybe there is something hidden
    responseType.add("password");

    Set<String> scopes = new HashSet(); //TODO how can we know the scope? does the call come from web or mobile?
    scopes.add(SocialScopes.WEB_SCOPE);

    OAuth2Request authorizationRequest = new OAuth2Request(
        authorizationParameters, SecurityConfig.CLIENT_ID,
        authorities, true, scopes, null, "",
        responseType, null);

    User userPrincipal = new User(
        userCredential.getEmail(), "", true, true, true, true, authorities);

    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        userPrincipal, null, authorities);

    OAuth2Authentication authenticationRequest = new OAuth2Authentication(
        authorizationRequest, authenticationToken);
    authenticationRequest.setAuthenticated(true);

    return tokenServices.createAccessToken(authenticationRequest);
  }

}
