package com.jj.barcabot.service.resttemplate.config.interceptor;

import static java.lang.String.format;

import java.io.IOException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * Custom RestTemplate request logger interceptor.
 */
@Log4j2
@Component
public class RequestLoggerInterceptor implements ClientHttpRequestInterceptor {

  private static final String REQUEST_BEGIN_MESSAGE = "%n===========================Request begin================================================%n"
      + "Request         : %s %s %n";
  private static final String HEADERS_MESSAGE = "Headers         : %s %n";
  private static final String REQUEST_END_MESSAGE = "==========================request end================================================%n";
  private static final String REQUEST_LOG_MESSAGE =
      REQUEST_BEGIN_MESSAGE
          + HEADERS_MESSAGE
          + REQUEST_END_MESSAGE;
  private static final String RESPONSE_BEGIN_MESSAGE = "%n============================response begin==========================================%n"
      + "Response   : %s %s %n";
  private static final String RESPONSE_END_MESSAGE = "=======================response end=================================================%n";
  private static final String RESPONSE_LOG_MESSAGE =
      RESPONSE_BEGIN_MESSAGE
          + HEADERS_MESSAGE
          + RESPONSE_END_MESSAGE;

  @Override
  public ClientHttpResponse intercept(@NonNull HttpRequest clientRequest, @NonNull byte[] body, @NonNull ClientHttpRequestExecution execution)
      throws IOException {

    logRequestDetails(clientRequest);

    ClientHttpResponse clientResponse = execution.execute(clientRequest, body);

    logResponseDetails(clientResponse);
    return clientResponse;
  }

  private void logRequestDetails(HttpRequest clientRequest) {
    log.info(format(REQUEST_BEGIN_MESSAGE, clientRequest.getMethod(), clientRequest.getURI()));
    log.debug(format(HEADERS_MESSAGE, clientRequest.getHeaders()));
    log.info(format(REQUEST_END_MESSAGE, clientRequest.getMethod(), clientRequest.getURI()));
  }

  private void logResponseDetails(ClientHttpResponse clientResponse) {
    try {
      log.info(format(RESPONSE_BEGIN_MESSAGE, clientResponse.getStatusCode(), clientResponse.getStatusText()));
      log.debug(format(HEADERS_MESSAGE, clientResponse.getHeaders()));
      log.info(format(RESPONSE_END_MESSAGE));
    } catch (IOException e) {
      log.error(e);
    }
  }

}
