package com.jj.barcabot.exception;

import org.springframework.http.HttpStatus;

/**
 * The type Core Runtime exception.
 */
public class CoreRuntimeException extends AbstractRuntimeException {

  /**
   * Instantiates a new NotFound exception.
   *
   * @param message the message
   */
  public CoreRuntimeException(String message) {
    this(message, null);
  }

  /**
   * Instantiates a new Not found exception.
   *
   * @param message the message
   * @param exception the exception
   */
  public CoreRuntimeException(String message, Throwable exception) {
    super(HttpStatus.INTERNAL_SERVER_ERROR, message, exception);
  }

}
