package com.jj.barcabot.exception;

import java.net.SocketException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;

/**
 * Runtime exception for connection issues.
 */
public class GatewayTimeoutException extends AbstractRuntimeException {

  /**
   * Verifies the exception e is raised due a network IO exception.
   *
   * @param e the exception
   * @return boolean to indicates the exception is due a network IO.
   */
  public static boolean isConnectionException(Throwable e) {

    return ExceptionUtils.indexOfThrowable(e, SocketException.class) != -1;
  }

  public GatewayTimeoutException(Exception e) {
    this(HttpStatus.GATEWAY_TIMEOUT.getReasonPhrase(), e);
  }

  public GatewayTimeoutException(String message) {
    this(message, null);
  }

  public GatewayTimeoutException(String message, Throwable exception) {
    super(HttpStatus.GATEWAY_TIMEOUT, message, exception);
  }

}
