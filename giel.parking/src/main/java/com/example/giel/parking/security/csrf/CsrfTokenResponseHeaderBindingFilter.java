package com.example.giel.parking.security.csrf;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

/**
 * Binds a {@link org.springframework.security.web.csrf.CsrfToken} to the {@link
 * HttpServletResponse} headers if the Spring {@link org.springframework.security.web.csrf.CsrfFilter}
 * has placed one in the {@link HttpServletRequest}.
 * <p>
 * Based on the work found in: <a href="http://stackoverflow.com/questions/20862299/with-spring-security-3-2-0-release-how-can-i-get-the-csrf-token-in-a-page-that">Stack
 * Overflow</a>
 *
 * @author Allan Ditzel
 * @since 1.0
 */
public class CsrfTokenResponseHeaderBindingFilter extends OncePerRequestFilter {

  protected static final String REQUEST_ATTRIBUTE_NAME = "_csrf";
  protected static final String RESPONSE_HEADER_NAME = "X-CSRF-HEADER";
  protected static final String RESPONSE_PARAM_NAME = "X-CSRF-PARAM";
  protected static final String RESPONSE_TOKEN_NAME = "X-CSRF-TOKEN";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      javax.servlet.FilterChain filterChain) throws ServletException, IOException {
    CsrfToken token = (CsrfToken) request.getAttribute(REQUEST_ATTRIBUTE_NAME);
    CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

    if (csrf != null) {
      Cookie cookie = WebUtils.getCookie(request, "X-CSRF-TOKEN");
      String csrftoken = csrf.getToken();

      if (cookie == null || csrftoken != null && !csrftoken.equals(cookie.getValue())) {
        cookie = new Cookie("X-CSRF-TOKEN", csrftoken);
        //TODO if cookie cant be read by the frontend in REACTJS, the cookie should be puth on HttpOnly = false;
        cookie.setPath("/");
        response.addCookie(cookie);
      }
    }

    if (token != null) {

      response.setHeader(RESPONSE_HEADER_NAME, token.getHeaderName());
      response.setHeader(RESPONSE_PARAM_NAME, token.getParameterName());
      response.setHeader(RESPONSE_TOKEN_NAME, token.getToken());
    }

    filterChain.doFilter(request, response);
  }
}
