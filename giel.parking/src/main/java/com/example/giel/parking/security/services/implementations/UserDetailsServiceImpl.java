package com.example.giel.parking.security.services.implementations;

import static com.example.giel.parking.constants.ResponseMessage.USER_DOES_NOT_EXISTS;
import static java.util.Objects.isNull;

import com.example.giel.parking.models.UserCredential;
import com.example.giel.parking.repositories.dao.UserCredentialsRepository;
import com.example.giel.parking.security.utils.JWTUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("UserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserCredentialsRepository userCredentialsRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserCredential user = userCredentialsRepository.findByEmail(email);

    if (isNull(user)) {
      throw new UsernameNotFoundException(USER_DOES_NOT_EXISTS);
    } else {
      return JWTUserFactory.createUser(user);
    }
  }

}
