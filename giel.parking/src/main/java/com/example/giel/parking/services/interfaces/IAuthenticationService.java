package com.example.giel.parking.services.interfaces;

import com.example.giel.parking.models.UserCredential;
import org.springframework.http.ResponseEntity;

public interface IAuthenticationService {

  UserCredential registerUser(UserCredential userCredentialsToRegister);

  ResponseEntity socialLogin(String email);

}
