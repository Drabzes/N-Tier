package com.example.giel.parking.security.configuration;

import static com.example.giel.parking.constants.EndPoints.API_DOC;
import static com.example.giel.parking.constants.EndPoints.FACEBOOK_CALLBACK;
import static com.example.giel.parking.constants.EndPoints.GOOGLE_CALLBACK;
import static com.example.giel.parking.constants.EndPoints.REGISTER_USER;
import static com.example.giel.parking.constants.EndPoints.SIGN_IN_PROVIDERS;
import static com.example.giel.parking.constants.Roles.ADMIN_ROLE;
import static com.example.giel.parking.constants.Roles.DEFAULT_ROLE;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@Configuration
//@EnableWebSecurity
public class AuthenticationConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private DataSource dataSource;

  @Value("${spring.queries.user-credentials-query}")
  private String userCredentialsQuery;

  @Value("${spring.queries.roles-query}")
  private String rolesQuery;

  private static final String[] AUTH_WHITELIST = {
      API_DOC,
      REGISTER_USER,
      SIGN_IN_PROVIDERS,
      FACEBOOK_CALLBACK,
      GOOGLE_CALLBACK
  };

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  @Bean
  public UserDetailsService userDetailsServiceBean() throws Exception {
    return super.userDetailsServiceBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication()
        .usersByUsernameQuery(userCredentialsQuery)
        .authoritiesByUsernameQuery(rolesQuery)
        .dataSource(dataSource)
        .passwordEncoder(bCryptPasswordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/auth/**").permitAll()
        .antMatchers("/user/me").permitAll()
        .antMatchers("/oauth/authorize").permitAll()
        .antMatchers(AUTH_WHITELIST).permitAll()
        .antMatchers("/testuserrole").hasRole(DEFAULT_ROLE)
        .antMatchers("/testadminrole").hasRole(ADMIN_ROLE)
        .anyRequest().authenticated();
  }
}