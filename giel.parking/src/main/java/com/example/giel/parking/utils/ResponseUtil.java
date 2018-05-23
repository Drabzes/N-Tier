package com.example.giel.parking.utils;


import static com.example.giel.parking.constants.ResponseMessage.SUCCESS;


import com.example.giel.parking.constants.JsonProperty;
import java.net.URI;
import java.util.Objects;
import javax.json.Json;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SuppressWarnings("unused")
public class ResponseUtil {

  private static final int OK_STATUS_CODE = HttpStatus.OK.value();
  private static final int BAD_REQUEST_STATUS_CODE = HttpStatus.BAD_REQUEST.value();
  private static final int CREATED_STATUS_CODE = HttpStatus.CREATED.value();
  private static final int CONFLICT_STATUS_CODE = HttpStatus.CONFLICT.value();
  private static final int NOT_FOUND_STATUS_CODE = HttpStatus.NOT_FOUND.value();

  public static ResponseEntity okResponse() {
    String successJson = Json.createObjectBuilder()
        .add(JsonProperty.STATUS_CODE, OK_STATUS_CODE)
        .add(JsonProperty.SUCCESS_MESSAGE, SUCCESS)
        .build().toString();

    return okResponse(successJson);
  }

  public static ResponseEntity okResponse(String responseMessage) {
    return ResponseEntity.ok().body(responseMessage);
  }

  public static ResponseEntity okResponse(Object responseBody) {
    return ResponseEntity.ok().body(responseBody);
  }

  public static ResponseEntity createdResponse(Object responseBody) {
    return ResponseEntity.created(URI.create(StringUtils.EMPTY)).body(responseBody);
  }

  public static ResponseEntity badResponse(String responseMessage) {
    return ResponseEntity.badRequest()
        .body(new OperationResult(BAD_REQUEST_STATUS_CODE, responseMessage));
  }

  public static ResponseEntity conflictResponse(String responseMessage) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(new OperationResult(CONFLICT_STATUS_CODE, responseMessage));
  }

  public static ResponseEntity noContentResponse() {
    return ResponseEntity.noContent().build();
  }

  public static ResponseEntity notFoundResponse(String responseMessage) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new OperationResult(NOT_FOUND_STATUS_CODE, responseMessage));
  }

  public static class OperationResult {

    private int errorCode;
    private String errorMessage;

    public OperationResult(int errorCode, String errorMessage) {
      this.errorCode = errorCode;
      this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
      return errorCode;
    }

    public void setErrorCode(int errorCode) {
      this.errorCode = errorCode;
    }

    public String getErrorMessage() {
      return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
      this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      OperationResult that = (OperationResult) o;
      return errorCode == that.errorCode &&
          Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {

      return Objects.hash(errorCode, errorMessage);
    }
  }

}