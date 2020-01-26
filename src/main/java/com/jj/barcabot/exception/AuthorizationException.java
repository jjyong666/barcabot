package com.jj.barcabot.exception;

import org.springframework.http.HttpStatus;

/**
 * The type Authorization exception.
 */
public class AuthorizationException extends AbstractRuntimeException {

  /**
   * Instantiates a new Authorization exception.
   *
   * @param message the message
   */
  public AuthorizationException(String message) {
    this(message, null);
  }

  /**
   * Instantiates a new Authorization exception.
   *
   * @param message the message
   * @param exception the exception
   */
  public AuthorizationException(String message, Throwable exception) {
    super(HttpStatus.FORBIDDEN, message, exception);
  }

}
