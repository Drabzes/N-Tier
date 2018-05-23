package com.example.giel.parking.security.services.implementations;

import static com.example.giel.parking.constants.ResponseMessage.JWT_TOKEN_NOT_PROVIDED;
import static com.example.giel.parking.constants.ResponseMessage.USER_DOES_NOT_EXISTS;
import static com.example.giel.parking.constants.SecurityConstants.AUTHORIZATION_HEADER;
import static java.util.Objects.isNull;
import com.example.giel.parking.models.UserCredential;
import com.example.giel.parking.repositories.dao.UserCredentialsRepository;
import com.example.giel.parking.security.models.CurrentUser;
import com.example.giel.parking.security.models.JWTAuthenticationRequest;
import com.example.giel.parking.security.services.interfaces.ITokenService;
import com.example.giel.parking.security.utils.JWTTokenUtil;
import io.jsonwebtoken.JwtException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements ITokenService {

  @Autowired
  public AuthenticationManager authenticationManager;

  @Autowired
  public JWTTokenUtil jwtTokenUtil;

  @Autowired
  @Qualifier("UserDetailsService")
  private UserDetailsService userDetailsService;

  @Autowired
  private UserCredentialsRepository userCredentialsRepository;

  @Override
  public CurrentUser getUserToken(JWTAuthenticationRequest authenticationRequest) {

    //Obtain authentication through the user and password
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
            authenticationRequest.getPassword()));

    //Set the authentication in the current security context
    SecurityContextHolder.getContext().setAuthentication(authentication);

    //Get the user details out of the spring context
    UserDetails userDetails = userDetailsService
        .loadUserByUsername(authenticationRequest.getEmail());

    //Generate the token for the logged in user
    String token = jwtTokenUtil.generateToken(userDetails);

    //Get the user from database
    UserCredential user = userCredentialsRepository.findByEmail(authenticationRequest.getEmail());

    //Put password to null
    user.setPassword(null);

    return new CurrentUser(token, user);
  }

  @Override
  public CurrentUser refreshJWToken(HttpServletRequest request) {
    String token = request.getHeader(AUTHORIZATION_HEADER);

    if (isNull(token)) {
      throw new JwtException(JWT_TOKEN_NOT_PROVIDED);
    }

    //Get username from token
    String username = jwtTokenUtil.getUsernameFromToken(token);

    //Get user by email
    UserCredential user = userCredentialsRepository.findByEmail(username);

    if (isNull(user)) {

    }

    user.setPassword(null);

    if (jwtTokenUtil.tokenCanBeUsed(token)) {
      String refreshToken = jwtTokenUtil.refreshToken(token);
      return new CurrentUser(refreshToken, user);
    } else {
      return null;
    }
  }

}
