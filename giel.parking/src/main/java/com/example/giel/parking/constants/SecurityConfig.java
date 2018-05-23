package com.example.giel.parking.constants;

import com.google.common.collect.Lists;
import java.util.List;

public class SecurityConfig {

    public static final String CLIENT_ID = "DevoParking";

    public static final String SECRET = "secret";

    public static final List<String> AUTHORIZATION_GRANT_TYPES = Lists
            .newArrayList("refresh_token", "password", "client_credentials");

    public static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";

    public static final String GRANT_TYPE_PASSWORD = "password";

    public static final String GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials";

    public static final List<String> OAUTH2_SCOPES = Lists.newArrayList("web", "mobile");

    public static final String WEB_SCOPE = "web";

    public static final String MOBILE_SCOPE = "mobile";

    public static final String KEYSTORE_NAME = "keystore.jks";

    public static final String KEYSTORE_PASSWORD = "98kSMFg6h3#gkM5@";

    public static final String KEYSTORE_SIGNATURE_ALIAS = "JWTSignature";

    public static final int ACCESS_TOKEN_VALIDITY = 600; //valid for 10 minutes

    public static final int REFRESH_TOKEN_VALIDITY = 3600 * 24 * 7; // valid for a week

}
