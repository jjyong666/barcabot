package com.jj.barcabot.exception;

import org.springframework.http.HttpStatus;

/**
 * The type Bad request exception.
 */
public class BadRequestException extends AbstractRuntimeException {

  /**
   * Instantiates a new BadRequest exception.
   *
   * @param message the message
   */
  public BadRequestException(String message) {
    this(message, null);
  }

  /**
   * Instantiates a new Bad request exception.
   *
   * @param message the message
   * @param exception the exception
   */
  public BadRequestException(String message, Throwable exception) {
    super(HttpStatus.BAD_REQUEST, message, exception);
  }

}
