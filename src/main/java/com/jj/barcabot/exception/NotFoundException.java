package com.jj.barcabot.exception;

import org.springframework.http.HttpStatus;

/**
 * The type Not found exception.
 */
public class NotFoundException extends AbstractRuntimeException {

  /**
   * Instantiates a new NotFound exception.
   *
   * @param message the message
   */
  public NotFoundException(String message) {
    this(message, null);
  }

  /**
   * Instantiates a new Not found exception.
   *
   * @param message the message
   * @param exception the exception
   */
  public NotFoundException(String message, Throwable exception) {
    super(HttpStatus.NOT_FOUND, message, exception);
  }

}
