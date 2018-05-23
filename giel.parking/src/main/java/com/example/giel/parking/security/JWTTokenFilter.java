package com.example.giel.parking.security;

import static com.example.giel.parking.constants.SecurityConstants.AUTHORIZATION_HEADER;
import static com.example.giel.parking.constants.SecurityConstants.TOKEN_BEARER_PREFIX;

import com.example.giel.parking.security.utils.JWTTokenUtil;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * OncePerRequestFilter ensures a single execution per request dispatch
 * <p>
 * This class checks for the authorization header and authenticates the JWT token and sets the
 * authentication in the context. Doing so will protect our APIs from those requests which do not
 * have any authorization token.
 */
public class JWTTokenFilter extends OncePerRequestFilter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JWTTokenUtil jwtTokenUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    // get the authorization header
    String token = request.getHeader(AUTHORIZATION_HEADER);

    // if empty token does not check
    if (token != null && !"".equals(token)) {

      if (token.startsWith(TOKEN_BEARER_PREFIX)) {
        token = token.replace(TOKEN_BEARER_PREFIX, "");
      }

      //retrieve the token username => email
      String username = jwtTokenUtil.getUsernameFromToken(token);

      //IF email does exists and person is not logged in

      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

        // retrieves user information via username
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

        //validate the token
        if (jwtTokenUtil.validateToken(token, userDetails)) {

          // if valid token sets the user authorization information in the security context of spring
          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
              userDetails, null, userDetails.getAuthorities());

          authenticationToken
              .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

          //LOGGER.info("JwtTokenFilter Authenticated user" + username + ", defining security context");
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }
      }
    }

    filterChain.doFilter(request, response);
  }
}
