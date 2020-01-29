
package com.jj.barcabot.service.football.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jj.barcabot.service.football.footballdata.dto.Competition;
import com.jj.barcabot.service.football.footballdata.dto.Season;
import com.jj.barcabot.service.football.footballdata.dto.Team;
import java.io.Serializable;
import java.time.ZonedDateTime;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NextMatchDto implements Serializable {

  private ZonedDateTime utcDate;
  private Competition competition;
  private Season season;
  private Team homeTeam;
  private Team awayTeam;

}
