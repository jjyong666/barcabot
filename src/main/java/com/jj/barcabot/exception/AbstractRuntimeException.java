package com.jj.barcabot.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * The type BaseRuntimeException.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractRuntimeException extends RuntimeException {

  protected final HttpStatus status;
  protected final String message;
  protected final Throwable exception;

  protected AbstractRuntimeException(HttpStatus status, String message, Throwable exception) {
    super(message, exception);
    this.status = status;
    this.message = message;
    this.exception = exception;
  }

  protected AbstractRuntimeException(String message) {
    this(HttpStatus.INTERNAL_SERVER_ERROR, message, null);
  }

  protected AbstractRuntimeException(HttpStatus status) {
    this(status, status.getReasonPhrase(), null);
  }

  protected AbstractRuntimeException(HttpStatus status, String message) {
    this(status, message, null);
  }

  protected AbstractRuntimeException(String message, Throwable exception) {
    this(HttpStatus.INTERNAL_SERVER_ERROR, message, exception);
  }

}
