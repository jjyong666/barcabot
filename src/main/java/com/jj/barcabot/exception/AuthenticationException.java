package com.jj.barcabot.exception;


import org.springframework.http.HttpStatus;

/**
 * The type Authentication exception.
 */
public class AuthenticationException extends AbstractRuntimeException {

  /**
   * Instantiates a new Authentication exception.
   *
   * @param message the message
   */
  public AuthenticationException(String message) {
    this(message, null);
  }

  /**
   * Instantiates a new Authentication exception.
   *
   * @param message the message
   * @param exception the exception
   */
  public AuthenticationException(String message, Throwable exception) {
    super(HttpStatus.UNAUTHORIZED, message, exception);
  }

}
