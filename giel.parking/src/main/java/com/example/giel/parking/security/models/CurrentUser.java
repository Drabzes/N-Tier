package com.example.giel.parking.security.models;

import com.example.giel.parking.models.UserCredential;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@NoArgsConstructor
public class CurrentUser {

  private String jwtToken;

  private UserCredential user;

  public CurrentUser(String jwtToken, UserCredential user) {
    this.jwtToken = jwtToken;
    this.user = user;
  }
}
