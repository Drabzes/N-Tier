package com.example.giel.parking.security.utils;

import static com.example.giel.parking.constants.SecurityConstants.ISSUER;
import static com.example.giel.parking.constants.SecurityConstants.TOKEN_BEARER_PREFIX;
import com.example.giel.parking.models.JwtUser;
import com.example.giel.parking.repositories.dao.UserCredentialsRepository;
import com.example.giel.parking.services.implementations.UserInfoServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JWTTokenUtil implements Serializable {

  private static final long serialVersionUID = 7794723381795755249L;

  // this is for the JWT claims
  private static final String CLAIM_KEY_SUBJECT = "sub";
  private static final String CLAIM_KEY_CREATED = "created";
  private static final String CLAIM_KEY_EXPIRED = "exp";
  private static final String CLAIM_KEY_UUID = "uuid";
  private static final String CLAIM_KEY_SCOPE = "scope";
  private static final String CLAIM_KEY_ISSUER = "iss";
  private static final String CLAIM_KEY_JWT_ID = "jti";

  @Autowired
  private UserCredentialsRepository userCredentialsRepository;

  @Autowired
  private UserInfoServiceImpl userClient;

  @Value("${jwt.secret:defaultsecretvalue}")
  private String secret;

  @Value("${jwt.expiration.oneday:86400}")
  private Long expirationTime;

  private String getCurrentUserUuid(String email) {
    return userClient.getUuidByUserInfoId(getCurrentUserInfoIdByEmail(email));
  }

  private Long getCurrentUserInfoIdByEmail(String email) {
    return userCredentialsRepository.findByEmail(email).getUserInfoId();
  }

  //Parse the JWT token and get the username out of it
  public String getUsernameFromToken(String token) {
    String username;

    try {
      username = getAllClaimsFromToken(token).getSubject();
    } catch (Exception ex) {
      username = "";
    }

    return username;
  }

  public Date getExpirationDateFromToken(String token) {
    Date expirationDate;

    try {
      Claims claims = getAllClaimsFromToken(token);
      expirationDate = claims.getExpiration();
    } catch (Exception ex) {
      expirationDate = null;
    }

    return expirationDate;
  }

  public String getUuidFromToken(String token) {
    return getAllClaimsFromToken(token).get(CLAIM_KEY_UUID).toString();
  }

  public String getIssuerFromToken(String token) {
    return getAllClaimsFromToken(token).get(CLAIM_KEY_ISSUER).toString();
  }

  public String getScopeFromToken(String token) {
    return getAllClaimsFromToken(token).get(CLAIM_KEY_SCOPE).toString();
  }

  //Here will the token content be created
  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    claims.put(CLAIM_KEY_SUBJECT, userDetails.getUsername());
    claims.put(CLAIM_KEY_CREATED, new Date());
    claims.put(CLAIM_KEY_UUID, getCurrentUserUuid(userDetails.getUsername()));
    claims.put(CLAIM_KEY_ISSUER, ISSUER);
    return getToken(claims);
  }

  public String refreshToken(String token) {
    Claims claims = getAllClaimsFromToken(token);
    claims.put(CLAIM_KEY_CREATED, new Date());
    return getToken(claims);
  }

  //Simple function that uses isTokenExpired function to say if it can be used or not
  public boolean tokenCanBeUsed(String token) {
    return (!isTokenExpired(token));
  }

  //check if existing token still valid is by comparing username which is the email-address and
  //expiring time should not be passed
  public boolean validateToken(String token, UserDetails userDetails) {
    JwtUser jwtUser = (JwtUser) userDetails;
    String username = getUsernameFromToken(token);

    return (username.equals(jwtUser.getUsername()) && !isTokenExpired(token));
  }

  //Helper function to create a JWT token with the given data
  private String getToken(Map<String, Object> claims) {
    Date creationDate = (Date) claims.get(CLAIM_KEY_CREATED);
    Date expirationDate = new Date(creationDate.getTime() + expirationTime * 1000);

    return Jwts.builder()
        .setClaims(claims)
        .setExpiration(expirationDate)
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }

  //Parse the JWT token to access the data
  private Claims getAllClaimsFromToken(String token) {
    if (token.startsWith(TOKEN_BEARER_PREFIX)) {
      token = token.replace(TOKEN_BEARER_PREFIX, StringUtils.EMPTY);
    }

    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }

  private boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

}
