package com.example.giel.parking.constants;

import com.google.common.collect.Lists;
import java.util.List;

public class SecurityConstants {

    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
  public static final String TOKEN_BEARER_PREFIX = "Bearer ";
  public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
    public static final String ISSUER = "DevoParking";

    public static final long EXPIRATIONTIME_10_DAYS = 864_000_000; // 10 days
    public static final long EXPIRATIONTIME_30_MINUTES = 1000 * 60 * 30; // 30 minutes
    public static final long EXPIRATIONTIME_5_MINUTES = 300; // 5 minutes


    public static final String CLIENT_ID = "DevoParking";
    public static final String SECRET = "secret";

    public static final List<String> AUTHORIZATION_GRANT_TYPES = Lists
            .newArrayList("refresh_token", "password", "client_credentials");
    public static final List<String> OAUTH2_SCOPES = Lists.newArrayList("web", "mobile");
    public static final String KEYSTORE_NAME = "keystore.jks";
    public static final String KEYSTORE_PASSWORD = "98kSMFg6h3#gkM5@";
    public static final String KEYSTORE_SIGNATURE_ALIAS = "JWTSignature";

    public static final int ACCESS_TOKEN_VALIDITY = 600; //valid for 10 minutes
    public static final int REFRESH_TOKEN_VALIDITY = 3600 * 24 * 7; // valid for a week

    public static final Long EXPIRATION_TIME_7_DAYS = new Long(604800); // valid for a week

    public static final int REFRESH_TOKEN_1_DAY = 3600 * 24; // 1 day = 86400 seconds
    public static final Long EXPIRATION_TIME_1_DAY = new Long(86400);

}
