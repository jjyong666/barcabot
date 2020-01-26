
package com.jj.barcabot.service.football.footballdata.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Head2head implements Serializable {

  private int numberOfMatches;
  private int totalGoals;
  private Team homeTeam;
  private Team awayTeam;

}
