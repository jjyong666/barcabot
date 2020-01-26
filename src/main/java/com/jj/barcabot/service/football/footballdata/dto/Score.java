
package com.jj.barcabot.service.football.footballdata.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Score implements Serializable {

  private String winner;
  private String duration;

}
