package com.jj.barcabot.service.resttemplate.config.error;

import static com.google.common.base.Strings.isNullOrEmpty;

import com.jj.barcabot.exception.AuthenticationException;
import com.jj.barcabot.exception.AuthorizationException;
import com.jj.barcabot.exception.BadRequestException;
import com.jj.barcabot.exception.CoreRuntimeException;
import com.jj.barcabot.exception.GatewayTimeoutException;
import com.jj.barcabot.exception.NotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

/**
 * The type Error response handler.
 */
@Log4j2
public class ResponseExceptionHandlerImpl implements ResponseExceptionHandler {

  @Override
  @SneakyThrows
  public void handleResponseError(ClientHttpResponse response) {
    HttpStatus httpStatus = getHttpStatus(response);
    if (httpStatus.is1xxInformational() || httpStatus.is2xxSuccessful()) {
      return;
    }

    log.info("Response Error: code: {}, text: {}, headers: {}", response.getStatusCode(), getHttpStatusText(response),
        response.getHeaders().toSingleValueMap());

    switch (httpStatus) {
      case NOT_FOUND:
      case GONE:
      case SERVICE_UNAVAILABLE:
      case UNAVAILABLE_FOR_LEGAL_REASONS:
        throw new NotFoundException(getMessage(response));

      case FORBIDDEN:
        throw new AuthorizationException(getMessage(response));

      case UNAUTHORIZED:
      case PROXY_AUTHENTICATION_REQUIRED:
      case NETWORK_AUTHENTICATION_REQUIRED:
        throw new AuthenticationException(getMessage(response));

      case CONFLICT:
      case BAD_REQUEST:
      case FAILED_DEPENDENCY:
      case LENGTH_REQUIRED:
      case TOO_MANY_REQUESTS:
      case BANDWIDTH_LIMIT_EXCEEDED:
      case HTTP_VERSION_NOT_SUPPORTED:
      case URI_TOO_LONG:
      case METHOD_NOT_ALLOWED:
      case UNSUPPORTED_MEDIA_TYPE:
      case NOT_ACCEPTABLE:
      case NOT_IMPLEMENTED:
      case EXPECTATION_FAILED:
      case PRECONDITION_FAILED:
      case PRECONDITION_REQUIRED:
      case REQUEST_HEADER_FIELDS_TOO_LARGE:
      case REQUESTED_RANGE_NOT_SATISFIABLE:
      case UPGRADE_REQUIRED:
        throw new BadRequestException(getMessage(response));

      case GATEWAY_TIMEOUT:
        throw new GatewayTimeoutException(getMessage(response));
      default:
        throw new CoreRuntimeException(getMessage(response));
    }
  }

  private HttpStatus getHttpStatus(ClientHttpResponse response) {
    try {
      return response.getStatusCode();
    } catch (IOException e) {
      throw new CoreRuntimeException("Error getting Status Code from response", e);
    }
  }

  private String getMessage(ClientHttpResponse response) {
    String httpStatusText = getHttpStatusText(response);
    String body = getBody(response);
    if (isNullOrEmpty(httpStatusText) && isNullOrEmpty(body)) {
      return null;
    }
    return String.format("%s: %s", httpStatusText, body);
  }

  private String getHttpStatusText(ClientHttpResponse response) {
    try {
      return response.getStatusText();
    } catch (IOException e) {
      log.warn("Error getting Status Text from response", e);
      return null;
    }
  }

  private String getBody(ClientHttpResponse response) {
    try {
      return extractImportantPart(IOUtils.toString(response.getBody(), StandardCharsets.UTF_8.name()));
    } catch (IOException e) {
      log.warn("Error reading body from response", e);
      return null;
    }
  }

  private String extractImportantPart(String fullBody) {
    return Optional.ofNullable(fullBody)
        .map(x -> x.replace("\"", ""))
        .map(x -> x.replace("}]}", ""))
        .map(this::getMessagePart)
        .orElse(null);

  }

  private String getMessagePart(String body) {
    String key = "message:";
    return Optional.of(body)
        .filter(x -> x.contains(key))
        .map(x -> x.indexOf(key) + key.length())
        .map(body::substring)
        .orElse(body);
  }
}
