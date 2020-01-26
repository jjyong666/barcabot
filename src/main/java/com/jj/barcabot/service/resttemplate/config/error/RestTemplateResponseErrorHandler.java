package com.jj.barcabot.service.resttemplate.config.error;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * The type Rest template response error handler.
 */
@RequiredArgsConstructor
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

  private final ResponseExceptionHandler responseExceptionHandler;

  @Override
  public boolean hasError(ClientHttpResponse response) throws IOException {
    return response.getStatusCode().series() == CLIENT_ERROR || response.getStatusCode().series() == SERVER_ERROR;
  }

  @Override
  public void handleError(@NonNull ClientHttpResponse response) {
    responseExceptionHandler.handleResponseError(response);
  }

}
