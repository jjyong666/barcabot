
package com.jj.barcabot.service.football.footballdata.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Goal implements Serializable {

  private int minute;
  private Object extraTime;
  private String type;
  private Team team;
  private Scorer scorer;
  private Assist assist;

}
