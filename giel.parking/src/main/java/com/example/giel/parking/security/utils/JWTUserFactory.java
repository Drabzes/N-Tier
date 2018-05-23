package com.example.giel.parking.security.utils;

import com.example.giel.parking.models.JwtUser;
import com.example.giel.parking.models.Role;
import com.example.giel.parking.models.UserCredential;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class JWTUserFactory {

    public static JwtUser createUser(UserCredential user) {
        return new JwtUser(user.getId(), user.getEmail(), user.getPassword(), mapRoles(user.getRoles()));
    }

    private static Set<GrantedAuthority> mapRoles(Set<Role> roles) {
      return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
          .collect(Collectors.toSet());
    }
}
