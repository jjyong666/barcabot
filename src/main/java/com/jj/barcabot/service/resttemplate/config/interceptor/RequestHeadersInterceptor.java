package com.jj.barcabot.service.resttemplate.config.interceptor;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * Custom RestTemplate request headers interceptor.
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class RequestHeadersInterceptor implements ClientHttpRequestInterceptor {

  private static final int HEADER_MAX_LENGTH = 255;
  private static final String TRACE_ID_HEADER = "X-B3-TraceId";

  private final HttpServletRequest servletRequest;

  @Override
  public ClientHttpResponse intercept(@NonNull HttpRequest clientRequest, @NonNull byte[] body, ClientHttpRequestExecution execution)
      throws IOException {

    setHeaderOnMissing(clientRequest, "Accept", MediaType.APPLICATION_JSON_VALUE);
    setHeaderOnMissing(clientRequest, "Content-Type", MediaType.APPLICATION_JSON_VALUE);

    setHeader(clientRequest, TRACE_ID_HEADER, getHeaderFromRequest(TRACE_ID_HEADER));

    return execution.execute(clientRequest, body);
  }

  private void setHeaderOnMissing(HttpRequest clientRequest, String key, String value) {
    if (!clientRequest.getHeaders().containsKey(key)) {
      setHeader(clientRequest, key, value);
    }
  }

  private void setHeader(HttpRequest clientRequest, String key, String value) {
    if (isEmpty(value)) {
      return;
    }

    log.debug("Adding the {} header with value {}", key, value);
    clientRequest.getHeaders().set(key, sanitizeHeaderValue(value));
  }

  private String sanitizeHeaderValue(String value) {
    if (value.length() > HEADER_MAX_LENGTH) {
      value = value.substring(0, HEADER_MAX_LENGTH);
      log.warn("Header size is bigger than {} characters. It was truncated.", HEADER_MAX_LENGTH);
    }

    value = value.replaceAll("\r", "").replaceAll("\n", "");

    return value;
  }

  private String getHeaderFromRequest(String key) {
    return servletRequest.getHeader(key);
  }

}
