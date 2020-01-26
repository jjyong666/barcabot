package com.jj.barcabot.service.resttemplate.config.error;

import org.springframework.http.client.ClientHttpResponse;

/**
 * The interface Error Response Handler.
 * <p>
 * This class will handle the response returned by RestTemplate and throw the corresponding Exceptions
 * </p>
 */
public interface ResponseExceptionHandler {

  /**
   * Handle the ClientHttpResponse errors.
   *
   * @param response the response returned by the client //
   */
  void handleResponseError(ClientHttpResponse response);

}
