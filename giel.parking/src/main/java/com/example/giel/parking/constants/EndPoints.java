package com.example.giel.parking.constants;

@SuppressWarnings("unused")
public class EndPoints {

  /**
   * API-PREFIX
   **/
  private static final String API_CREATE_PREFIX = "/create";
  private static final String API_ADD_PREFIX = "/add";
  private static final String API_UPDATE_PREFIX = "/update";
  private static final String API_DELETE_PREFIX = "/delete";

  /**
   * CONTROLLER-PREFIX
   **/
  public static final String SERVICE_TYPE_PREFIX = "/service_types";
  public static final String USERS_PREFIX = "/users";
  public static final String PAYMENT_METHODS_PREFIX = "/payment_methods";
  public static final String PRIVILEGES_PREFIX = "/privileges";
  public static final String SCANNING_PREFIX = "/scanning";
  public static final String SERVICES_PREFIX = "/services";
  public static final String CITIES_PREFIX = "/cities";
  public static final String REGIONS_PREFIX = "/regions";
  public static final String VOUCHERS_PREFIX = "/vouchers";
  public static final String HISTORY_PREFIX = "/history";
  public static final String BALANCE_PREFIX = "/balance";
  public static final String INVOICES_PREFIX = "/invoices";
  public static final String QR_PREFIX = "/qr";
  public static final String TOKEN_PREFIX = "/api/authentication-service/token";

  /**
   * USER-SERVICE
   **/
  public static final String CREATE_EMPTY_USER = API_CREATE_PREFIX + "/empty_user";
  public static final String UPDATE_USER_FIRSTNAME_LASTNAME =
      API_UPDATE_PREFIX + "/firstname_lastname";
  public static final String UPDATE_USER_BENEFICIARIES = API_UPDATE_PREFIX + "/beneficiaries";
  public static final String UPDATE_USER_PRIVILEGES = API_UPDATE_PREFIX + "/beneficiary";
  public static final String UPDATE_PAYMENT_METHODS_OF_USER = API_UPDATE_PREFIX + "/user/{uuid}";
  public static final String ADD_USER_BENEFICIARIES = API_ADD_PREFIX + "/beneficiaries";
  public static final String CREATE_NEW_PAYMENT_METHOD = API_CREATE_PREFIX + "/new";
  public static final String DELETE_PAYMENT_METHODS_OF_USER = API_DELETE_PREFIX + "/{uuid}";
  public static final String GET_USER_PRIVILEGES = "/beneficiary";
  public static final String GET_ALL_PAYMENT_METHODS = "/all";
  public static final String GET_UUID_BY_USER_INFO_ID = "/id/{userInfoId}";
  public static final String GET_USERNAME_BY_USER_INFO_ID = "/name/{userInfoId}";
  public static final String GET_USER_INFO_ID_BY_UUID = "/uuid/{uuid}";
  public static final String GET_CUSTOMER_ID_BY_UUID = "/customer/{uuid}";
  public static final String GET_SERVICE_TYPES_BY_UUID = "/{uuid}";
  public static final String GET_USER_INFO_BY_UUID = "/user/{uuid}";
  public static final String GET_SERVICE_TYPE_NAME_BY_ID = "/id/{serviceTypeId}";
  public static final String GET_ALL_USER_INFO_IDS = "/id/list";
  public static final String VALIDATE_USER_PRIVILEGES = "/validate";

  /**
   * QR-SERVICE
   **/
  public static final String GENERATE_QR = "/generate";
  public static final String SCANNING_OUT = "/out";
  public static final String SCANNING_IN = "/in";
  public static final String GET_USER_HISTORY = "/mine/{uuid}";
  public static final String GET_BENEFICIARY_HISTORY = "/beneficiary/{uuid}";
  public static final String GET_LAST_SERVICE_NAME = "/last/service/{uuid}";
  public static final String GET_CURRENT_BALANCE_BY_UUID = "/current/{uuid}";
  public static final String GET_PREVIOUS_BALANCE_BY_ID = "/previous/{userInfoId}";

  /**
   * PAYMENT-SERVICE
   **/
  public static final String GET_ALL_VOUCHERS_BY_USER_ID = "/user/{userId}";
  public static final String GET_ALL_BOUGHT_VOUCHERS_BY_BUYER = "/buyer/{uuid}";
  public static final String GET_VOUCHER_VALUE_BY_UUID = "/value/{uuid}";
  public static final String BUY_VOUCHER = "/buy";
  public static final String UPDATE_VOUCHER_ACTUAL_AMOUNT = API_UPDATE_PREFIX + "/amount/actual";
  public static final String ASSIGN_VOUCHER_TO_USER = "/assign";
  public static final String INVOICE_HISTORY = "/history/{uuid}";

  /**
   * PARKING-SERVICE
   **/
  public static final String CREATE_NEW_SERVICE_INFO = "/create";
  public static final String UPDATE_SERVICE_INFO_BY_ID = "/update";
  public static final String FIND_SERVICE_INFO_BY_ID = "/id/{serviceInfoId}";
  public static final String GET_SERVICE_PRICE_RATE_BY_ID = "/price/{serviceInfoId}";
  public static final String GET_SERVICE_NAME_BY_ID = "/name/{serviceInfoId}";
  public static final String CHECK_SERVICE_TYPE_ID = "/check/{serviceTypeId}";
  public static final String CHECK_SERVICE_IDS = "/check";
  public static final String GET_SERVICES_BY_SERVICE_TYPE_ID =
      SERVICES_PREFIX + "/{serviceTypeId}";
  public static final String GET_SERVICE_TYPE_ID_BY_SERVICE =
      SERVICE_TYPE_PREFIX + "{serviceInfoId}";
  public static final String GET_SERVICES_BY_CITY_ID = SERVICES_PREFIX + "/{cityId}";
  public static final String GET_SERVICES_BY_REGION_ID = SERVICES_PREFIX + "/{regionId}";
  public static final String GET_CITIES_BY_REGION_ID = CITIES_PREFIX + "/{regionId}";

  /**
   * UTHENTICATION-SERVICE
   **/
  public static final String REGISTER_USER = "/register";
  public static final String API_DOC = "/**/v2/api-docs";
  public static final String SIGN_IN_PROVIDERS = "/api/authentication-service/sign_in/{providerId}";
  public static final String GOOGLE_CALLBACK = "/google";
  public static final String GOOGLE_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
  public static final String FACEBOOK_CALLBACK = "/api/authentication-service/facebook";
  public static final String GENERATE_JWT_TOKEN = "/generate-token";
  public static final String REFRESH_JWT_TOKEN = "/refresh";
  public static final String TOKEN_WHITE_LIST = "/token/*";

  /**
   * User-Client
   **/
  public static final String GET_USERNAME_BY_USER_INFO_ID_CLIENT =
      USERS_PREFIX + GET_USERNAME_BY_USER_INFO_ID;

  public static final String GET_USER_INFO_ID_BY_UUID_CLIENT =
      USERS_PREFIX + GET_USER_INFO_ID_BY_UUID;

  public static final String GET_CUSTOMER_ID_BY_UUID_CLIENT =
      USERS_PREFIX + GET_CUSTOMER_ID_BY_UUID;

  public static final String GET_ALL_USER_INFO_IDS_CLIENT = USERS_PREFIX + GET_ALL_USER_INFO_IDS;

  public static final String GET_USER_PRIVILEGES_CLIENT = PRIVILEGES_PREFIX + GET_USER_PRIVILEGES;

  public static final String GET_SERVICE_TYPES_BY_UUID_CLIENT =
      SERVICE_TYPE_PREFIX + GET_SERVICE_TYPES_BY_UUID;

  public static final String CREATE_EMPTY_USER_CLIENT =
          USERS_PREFIX + CREATE_EMPTY_USER;

  public static final String GET_UUID_BY_USER_INFO_ID_CLIENT = USERS_PREFIX + GET_UUID_BY_USER_INFO_ID;

  public static final String VALIDATE_USER_PRIVILEGES_CLIENT =
      PRIVILEGES_PREFIX + VALIDATE_USER_PRIVILEGES;

  /**
   * ServiceInfo-Client
   **/
  public static final String GET_SERVICE_PRICE_RATE_BY_ID_CLIENT =
      SERVICES_PREFIX + GET_SERVICE_PRICE_RATE_BY_ID;

  public static final String GET_SERVICE_NAME_BY_ID_CLIENT =
      SERVICES_PREFIX + GET_SERVICE_NAME_BY_ID;

  public static final String GET_SERVICE_TYPE_ID_BY_SERVICE_CLIENT =
      SERVICES_PREFIX + GET_SERVICE_TYPE_ID_BY_SERVICE;

  public static final String CHECK_SERVICE_IDS_CLIENT =
      SERVICES_PREFIX + CHECK_SERVICE_IDS;

  /**
   * Payment-Client
   **/
  public static final String GET_ALL_VOUCHERS_BY_USER_ID_CLIENT =
      VOUCHERS_PREFIX + GET_ALL_VOUCHERS_BY_USER_ID;

  public static final String UPDATE_VOUCHER_ACTUAL_AMOUNT_CLIENT =
      VOUCHERS_PREFIX + UPDATE_VOUCHER_ACTUAL_AMOUNT;

  /**
   * QR-Client
   **/
  public static final String GET_PREVIOUS_BALANCE_BY_ID_CLIENT =
      BALANCE_PREFIX + GET_PREVIOUS_BALANCE_BY_ID;

  /**
   * ServiceType_Client
   */
  public static final String GET_ALL_SERVICE_TYPES =
      SERVICE_TYPE_PREFIX;

  public static final String CHECK_SERVICE_TYPE_ID_BY_ID =
      SERVICE_TYPE_PREFIX + CHECK_SERVICE_TYPE_ID;

  public static final String GET_SERVICE_TYPE_NAME_BY_ID_CLIENT =
      SERVICE_TYPE_PREFIX + GET_SERVICE_TYPE_NAME_BY_ID;

}
