package com.example.giel.parking.security.configuration;

import static com.example.giel.parking.constants.EndPoints.API_DOC;
import static com.example.giel.parking.constants.EndPoints.CREATE_EMPTY_USER;
import static com.example.giel.parking.constants.EndPoints.FACEBOOK_CALLBACK;
import static com.example.giel.parking.constants.EndPoints.GET_USER_INFO_BY_UUID;
import static com.example.giel.parking.constants.EndPoints.GOOGLE_CALLBACK;
import static com.example.giel.parking.constants.EndPoints.REGISTER_USER;
import static com.example.giel.parking.constants.EndPoints.SIGN_IN_PROVIDERS;
import static com.example.giel.parking.constants.EndPoints.TOKEN_WHITE_LIST;

import com.example.giel.parking.security.JWTAuthenticationEntryPoint;
import com.example.giel.parking.security.JWTTokenFilter;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

/**
 * By using @EnableGlobalMethodSecurity(prePostEnabled = true)
 * <p>
 * We activate @PreFilter, @PreAuthorize, @PostFilter, @PostAuthorize annotations on any spring beans in the context
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JWTAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.usercredentials-query}")
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

    /**
     * All these urls will be ignored for CSRF protection
     *
     * with ** pattern you say, for all url's after this prefix.
     *
     * All get,
     */
    private String[] ignoreCsrfMatchers = {
            "/token/**",
            "/oauth/**",
            "/auth/**"
    };

    @Autowired
    @Qualifier("UserDetailsService")
    private UserDetailsService userDetailsService;


    /**
     * The declaration of the provider created by the authenticationManager is used by our filter to authenticate users.
     *
     * @return
     *
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery(this.userCredentialsQuery)
                .authoritiesByUsernameQuery(this.rolesQuery)
                .dataSource(this.dataSource)
                .passwordEncoder(this.bCryptPasswordEncoder);

        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public JWTTokenFilter jwtTokenFilterBean() {
      return new JWTTokenFilter();
    }


    /**
     * We define the login and signup endpoints to skip security; even “anonymous” should be able to do these two operations.
     * <p>
     * We define the filter chain applied to all requests while adding two important configs:
     * Entry point reference
     * and
     * setting the session creation to stateless (we do not want the session created for security purposes as we are using tokens for each request).
     * </p>
     * <p>
     * We do not need csrf protection because our tokens are immune to it.
     * </p>
     *
     * <p>
     *
     * </p>
     *
     * @param http
     *
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // entry points
        http
                .antMatcher("/facebook").anonymous()
                .and()
                .antMatcher("/google").anonymous()
                .and()
                .antMatcher("/**/google").anonymous()
                .and()
                .antMatcher("/signin/**").anonymous()
                .and()
                .authorizeRequests()
            .antMatchers(new String[]{GET_USER_INFO_BY_UUID, CREATE_EMPTY_USER}).permitAll()
            .antMatchers(TOKEN_WHITE_LIST).permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated();

        // If a user try to access a resource without having enough permissions
        http
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .addFilterBefore(jwtTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
                // this disables session creation on Spring Security
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .csrf()
//                .csrfTokenRepository(csrfTokenRepository())
//                .ignoringAntMatchers(ignoreCsrfMatchers)
//                .and()
//                // CSRF tokens handling
//                .addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(), CsrfFilter.class)

        .csrf().disable()
        ;

        //http.headers().cacheControl();

    }


    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        /**
         * You'll see references to the X-XSRF-TOKEN header in the javascript code.
         */
        repository.setHeaderName("X-CSRF-TOKEN");
        return repository;
    }
}
