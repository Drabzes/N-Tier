package com.example.giel.parking.constants;

@SuppressWarnings("unused")
public class ApiDoc {

  /**
   * USER-SERVICE
   **/
  public static final String CREATE_NEW_PAYMENT_METHOD_API_DOC = "    {\n"
      + "        \"name\":\"test_payment\"\n"
      + "    }\n";

  public static final String UPDATE_PAYMENT_METHODS_OF_USER_API_DOC =
      "    uuid: a14acc81-e2d4-435a-aebb-ef1377dd09fc\n"
          + "    paymentMethodIds: [2 , 1 , 3]\n";

  public static final String UPDATE_USER_PRIVILEGES_API_DOC = "    {\n"
      + "        \"uuid\": \"a14acc81-e2d4-435a-aebb-ef1377dd09fc\",\n"
      + "        \"beneficiaries\": [\n"
      + "            {\n"
      + "                \"uuid\": \"9f30a739-6df0-49cb-862e-ec330de737ca\",\n"
      + "                \"privileges\": [1, 2]\n"
      + "            }\n"
      + "        ]\n"
      + "    }\n";

  public static final String UPDATE_USER_BENEFICIARIES_API_DOC = "    {\n"
      + "    \t\"uuid\": \"a14acc81-e2d4-435a-aebb-ef1377dd09fc\",\n"
      + "    \t\"beneficiaries\": [\n"
      + "    \t\t{\n"
      + "    \t\t\t\"uuid\": \"9f30a739-6df0-49cb-862e-ec330de737ca\",\n"
      + "    \t\t\t\"privileges\": [\n"
      + "    \t\t\t\t{\n"
      + "    \t\t\t\t\t\"serviceTypeId\": 1,\n"
      + "    \t\t\t\t\t\"privilegeList\": [\n"
      + "                            {\n"
      + "                                \"serviceId\": 2,\n"
      + "                                \"days\": {\n"
      + "                                    \"WEDNESDAY\": \"BEFORE_NOON\",\n"
      + "                                    \"MONDAY\": \"ALL\",\n"
      + "                                    \"SATURDAY\": \"AFTER_NOON\",\n"
      + "                                    \"FRIDAY\": \"BEFORE_NOON\",\n"
      + "                                    \"TUESDAY\": \"WORKING_HOURS\",\n"
      + "                                    \"SUNDAY\": \"ALL\",\n"
      + "                                    \"THURSDAY\": \"ALL\"\n"
      + "                                }\n"
      + "                            }\n"
      + "                        ]\n"
      + "    \t\t\t\t}\n"
      + "    \t\t\t]\n"
      + "    \t\t}\n"
      + "    \t]\n"
      + "    }";

  public static final String UPDATE_USER_FIRSTNAME_LASTNAME_API_DOC = "    {\n"
      + "        \"uuid\": \"a14acc81-e2d4-435a-aebb-ef1377dd09fc\",\n"
      + "        \"lastName\": \"Verhagen\",\n"
      + "        \"firstName\": \"Jef\"\n"
      + "    }\n";

  /**
   * QR-SERVICE
   **/
  public static final String GENERATE_QR_API_DOC = "    {\n"
      + "        \"uuid\":\"9f30a739-6df0-49cb-862e-ec330de737ca\"   \n"
      + "    }\n";

  public static final String SCANNING_IN_API_DOC = "    {\n"
      + "        \"qrCode\": {\n"
      + "            \"uuid\":\"a14acc81-e2d4-435a-aebb-ef1377dd09fc\"\n"
      + "        },\n"
      + "        \"serviceId\":\"1\"\n"
      + "    }\n";

  public static final String SCANNING_OUT_API_DOC = "    {\n"
      + "        \"qrCode\": {\n"
      + "            \"uuid\":\"a14acc81-e2d4-435a-aebb-ef1377dd09fc\"\n"
      + "        },\n"
      + "        \"serviceId\":\"1\"\n"
      + "    }\n"
      + "    \n"
      + "    OR  \n"
      + "    {\n"
      + "        \"qrCode\":{\n"
      + "            \"uuid\":\"9f30a739-6df0-49cb-862e-ec330de737ca\",\n"
      + "            \"splitBillingQRCodeList\":[\n"
      + "                {\n"
      + "                    \"uuid\":\"9f30a739-6df0-49cb-862e-ec330de737ca\"\n"
      + "                },\n"
      + "                {\n"
      + "                    \"uuid\":\"9f30a739-6df0-49cb-862e-ec330de737ca\"\n"
      + "                },\n"
      + "                {\n"
      + "                    \"uuid\":\"9f30a739-6df0-49cb-862e-ec330de737ca\"\n"
      + "                }\n"
      + "            ]\n"
      + "        },\n"
      + "        \"serviceId\":\"1\"\n"
      + "    }\n";

  /**
   * PAYMENT-SERVICE
   **/
  public static final String ASSIGN_VOUCHER_TO_USER_API_DOC = "    {\n"
      + "        \"id\": 2,       → this is the id of the voucher\n"
      + "        \"userId\": 2\n"
      + "    }\n";

  public static final String BUY_VOUCHER_API_DOC = "    {\n"
      + "        \"buyAmount\": 50,\n"
      + "        \"buyerId\": 1,\n"
      + "        \"serviceTypeId\": 1\n"
      + "    }\n";

  public static final String UPDATE_VOUCHER_ACTUAL_AMOUNT_API_DOC = "    {\n"
      + "        \"id\": 2,       → this is the id of the voucher\n"
      + "        \"actualAmount\": 8\n"
      + "    }\n";

  /**
   * PARKING-SERVICE
   **/

  public static final String CREATE_NEW_SERVICE_INFO_API_DOC = "    {\n"
      + "        \"name\": \"Devoteam parking Ninove\",\n"
      + "        \"street\": \"kruisvijverstraat\",\n"
      + "        \"houseNumber\": \"22\",\n"
      + "        \"city\": \"Ninove\",\n"
      + "        \"region\": \"Oost-Vlaanderen\",\n"
      + "        \"country\": \"Belgium\",\n"
      + "        \"zipCode\": \"9400\",\n"
      + "        \"active\": true,\n"
      + "        \"fixedPriceRate\": 6.0\n"
      + "    }";

  public static final String UPDATE_SERVICE_INFO_BY_ID_API_DOC = "    {\n"
      + "        \"id\": 1,\n"
      + "        \"name\": \"Devoteam parking Ninove\",\n"
      + "        \"street\": \"kruisvijverstraat\",\n"
      + "        \"houseNumber\": \"22\",\n"
      + "        \"city\": \"Ninove\",\n"
      + "        \"region\": \"Oost-Vlaanderen\",\n"
      + "        \"country\": \"Belgium\",\n"
      + "        \"zipCode\": \"9400\",\n"
      + "        \"active\": true,\n"
      + "        \"fixedPriceRate\": 6.0\n"
      + "    }";
  /**
   * AUTHENTICATION-SERVICE
   **/


}
