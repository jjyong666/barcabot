package com.jj.barcabot.exception.handler;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

import com.jj.barcabot.exception.AbstractRuntimeException;
import com.jj.barcabot.exception.CoreRuntimeException;
import com.jj.barcabot.exception.dto.ErrorObject;
import com.jj.barcabot.exception.dto.ErrorResponse;
import java.time.Instant;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@ControllerAdvice
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(AbstractRuntimeException.class)
  public ResponseEntity<ErrorResponse> handleGravityException(WebRequest webRequest, AbstractRuntimeException exception) {
    log.error(exception);
    return new ResponseEntity<>(createErrorResponse(webRequest, exception), exception.getStatus());
  }

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<ErrorResponse> handleThrowable(WebRequest webRequest, Throwable throwable) {
    log.error(throwable);
    return new ResponseEntity<>(createErrorResponse(webRequest, mapAbstractRuntimeException(throwable)), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private ErrorResponse createErrorResponse(WebRequest webRequest, AbstractRuntimeException exception) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setTraceId(getTraceId(webRequest));
    errorResponse.setTimestamp(Instant.now().toEpochMilli());
    errorResponse.setError(buildErrorObject(exception));
    return errorResponse;
  }

  private ErrorObject buildErrorObject(AbstractRuntimeException exception) {
    ErrorObject error = new ErrorObject();
    error.setCode(exception.getStatus().value());
    error.setMessage(exception.getMessage());
    error.setDescription(Optional.ofNullable(exception.getException()).map(Throwable::getMessage).orElse(null));
    return error;
  }

  private String getTraceId(WebRequest webRequest) {
    return Optional.ofNullable(webRequest)
        .map(x -> x.getHeader("X-B3-TraceId"))
        .filter(StringUtils::isNotEmpty)
        .orElseGet(() -> randomAlphanumeric(10));
  }

  private AbstractRuntimeException mapAbstractRuntimeException(Throwable throwable) {
    return new CoreRuntimeException(throwable.getMessage(), throwable);
  }

}
