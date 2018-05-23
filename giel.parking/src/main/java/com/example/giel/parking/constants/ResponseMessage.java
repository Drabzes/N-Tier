package com.example.giel.parking.constants;

@SuppressWarnings("unused")
public class ResponseMessage {

  public static final String SUCCESS = "Success";
  public static final String INVALID_UUID = "Invalid unique identifier ";
  public static final String INVALID_USER_INFO_ID = "Invalid user info id ";
  public static final String INVALID_BENEFICIARY_UUID = "Invalid beneficiary unique identifier ";
  public static final String INVALID_PAYMENT_METHOD_ID = "Invalid payment method id ";
  public static final String INVALID_SERVICE_TYPE_ID = "Invalid serviceType id ";
  public static final String INVALID_VOUCHER_ID = "Invalid voucher id ";
  public static final String INVALID_CITY_ID = "Invalid city id ";
  public static final String INVALID_REGION_ID = "Invalid region id ";
  public static final String EMPTY_CUSTOMER_ID = "Empty customerId";
  public static final String EMPTY_UUID = "Empty uuid";
  public static final String EMPTY_PAYMENT_METHOD_IDS = "Empty paymentMethodIds";
  public static final String EMPTY_BENEFICIARY_ID = "Empty beneficiaryId";
  public static final String EMPTY_FIRST_NAME = "Empty firstName";
  public static final String EMPTY_LAST_NAME = "Empty lastName";
  public static final String EMPTY_PAYMENT_METHOD_NAME = "Empty paymentMethod name";
  public static final String EMPTY_BENEFICIARIES = "Empty beneficiaries";
  public static final String USER_IS_NOT_A_CUSTOMER = "The user is not a costumer";
  public static final String USER_IS_NOT_A_BENEFICIARY_OF_CUSTOMER = "A beneficiary is not a beneficiary of the given customer";
  public static final String USER_ALREADY_HAS_CUSTOMER = "A given beneficiary already has a customer";
  public static final String USER_ALREADY_BENEFICIARY_OF_CUSTOMER = "A given beneficiary is already beneficiary of the customer";
  public static final String VOUCHERS_LIST_NOT_FOUND = "Could not find any vouchers";
  public static final String VOUCHER_NOT_FOUND = "Could not find requested voucher";
  public static final String ERROR_GENERATING_QR_CODE = "Something went wrong while generating the QR-code";
  public static final String EMPTY_QR_CODE = "Empty qrCode";
  public static final String EMPTY_SERVICE_ID = "Empty serviceId";
  public static final String EMPTY_SERVICE_TYPE_ID = "Empty serviceTypeId";
  public static final String USER_SERVICE_NOT_AVAILABLE = "User-service not available";
  public static final String PAYMENT_SERVICE_NOT_AVAILABLE = "Payment-service not available";
  public static final String USER_DOES_NOT_HAVE_PRIVILEGES = "User does not have privileges";
  public static final String PARKING_SERVICE_NOT_AVAILABLE = "Parking-service not available";
  public static final String SERVICE_NOT_ACTIVE = "This service is not active temporary or permanently";
  public static final String INVALID_SERVICE_ID = "Invalid serviceId";
  public static final String NO_JSON_UUID_PROPERTY_PRESENT = "No uuid json property present";
  public static final String EXPIRED_JWT_TOKEN_ERROR = "The JWT token is expired. Ask for a new one.";
  public static final String EMAIL_MISSING_IN_JWT_TOKEN = "The email is missing from the JWT token.";
  public static final String USER_DOES_NOT_EXISTS = "User with the current email does not exist and cant get a JWT token.";
  public static final String JWT_TOKEN_NOT_PROVIDED = "The JWT Token is not provided and cannot be refreshed.";
  public static final String JWT_TOKEN_EXPIRED_AND_CANNOT_REFRESH = "The JWT Token is expired and cant be refreshed. Please ask a new one.";

  public static final String WELCOME_TITLE = "Welcome";
  public static final String WELCOME_MESSAGE = "Welcome to %s.";

  public static final String SCANNING_CONFLICT_TITLE = "Scanning conflict";
  public static final String ENTRY_ALREADY_PRESENT = "There already is an entry present.";
  public static final String NO_PARKING_ENTRY_FOUND = "No parking entry found.";

  public static final String BYE_TITLE = "Bye";
  public static final String BYE_MESSAGE = "Thank you for visiting. We are looking forward to your next visit.";

  public static final String INSUFFICIENT_PRIVILEGES_MESSAGE_TITLE = "Authorization conflict";
  public static final String INSUFFICIENT_PRIVILEGES_MESSAGE = "You have insufficient privileges.";

  public static final String USER_NAME_NOT_AVAILABLE = "User name not available";
  public static final String SERVICE_TYPE_NAME_NOT_AVAILABLE = "ServiceType name not available";
  public static final String SERVICE_NAME_NOT_AVAILABLE = "Service name not available";
  public static final String QR_SERVICE_NOT_AVAILABLE = "QR-service not available";
  public static final String INVALID_SERVICE_INFO_ID = "Invalid serviceInfo id ";
  public static final String EMPTY_PASSWORD = "Empty password";
  public static final String EMPTY_EMAIL = "Empty email";
  public static final String INVALID_EMAIL_FORMAT = "Invalid email address format";
  public static final String INVALID_PASSWORD_FORMAT = "The password must contain at least 8 characters, maximum 60. It must be composed of at least 1 lowercase letter, 1 uppercase letter, 1 number and 1 special character [#?!@$\\.;:%^&*-_]";
  public static final String EMAIL_ALREADY_IN_USE = "Email address already in use";

  public static final String HAS_NO_ACCESS_TO = " Has no access to ";

}
