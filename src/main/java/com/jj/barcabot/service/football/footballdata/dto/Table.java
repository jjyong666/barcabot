
package com.jj.barcabot.service.football.footballdata.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Table implements Serializable {

  private int position;
  private Team team;
  private int playedGames;
  private int won;
  private int draw;
  private int lost;
  private int points;
  private int goalsFor;
  private int goalsAgainst;
  private int goalDifference;

}
