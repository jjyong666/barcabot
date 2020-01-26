package com.jj.barcabot.exception.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class ErrorObject implements Serializable {

  private int code;
  private String message;
  private String description;

}
