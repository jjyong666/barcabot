package com.jj.barcabot.exception.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class ErrorResponse implements Serializable {

  private String traceId;
  private long timestamp;
  private ErrorObject error;

}
