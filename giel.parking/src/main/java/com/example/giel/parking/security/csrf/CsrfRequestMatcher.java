package com.example.giel.parking.security.csrf;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

//http://blog.netgloo.com/2014/09/28/spring-boot-enable-the-csrf-check-selectively-only-for-some-requests/
public class CsrfRequestMatcher implements RequestMatcher {

  private AntPathRequestMatcher[] requestMatchers;

  public CsrfRequestMatcher(AntPathRequestMatcher[] requestMatcher) {
    this.requestMatchers = requestMatcher;
  }

  @Override
  public boolean matches(HttpServletRequest request) {

       /* // If the request match one url the CSRF protection will be enabled
        for (AntPathRequestMatcher rm : requestMatchers) {
            LOGGER.debug("requestmatcher foreach is : " + rm.toString());
            if (rm.matches(request)) {
                LOGGER.debug("requestmatcher of : " + rm.toString() + " of request : " + request.toString());
                return false;
                //return true;
            }
        }
        //return false;
        return true;*/

    return returnTrueIfRequestMatchesWith_PROTECTED_CsrfUrl(request);
    //return returnTrueIfRequestMatchesWith_UNPROTECTED_CsrfUrl(request);

  }

  //disable CSRF protection on the given urls
  public boolean returnTrueIfRequestMatchesWith_UNPROTECTED_CsrfUrl(HttpServletRequest request) {
    // If the request match one url the CSRF protection will be disabled
    for (AntPathRequestMatcher rm : requestMatchers) {
      if (rm.matches(request)) {
        return false;
      }
    }
    return true;
  }

  //enable csrf protection on the given urls
  public boolean returnTrueIfRequestMatchesWith_PROTECTED_CsrfUrl(HttpServletRequest request) {
// If the request match one url the CSRF protection will be enabled
    for (AntPathRequestMatcher rm : requestMatchers) {
      if (rm.matches(request)) {
        return true;
      }
    }
    return false;
  }

}
