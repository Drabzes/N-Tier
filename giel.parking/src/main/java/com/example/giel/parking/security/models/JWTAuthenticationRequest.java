package com.example.giel.parking.security.models;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class JWTAuthenticationRequest implements Serializable {

  private static final long serialVersionUID = -7748808965044646280L;

  private String email;
  private String password;

  public JWTAuthenticationRequest() {
    super();
  }

  public JWTAuthenticationRequest(String email, String password) {
    super();
    this.email = email;
    this.password = password;
  }

}
