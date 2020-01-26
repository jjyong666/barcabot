
package com.jj.barcabot.service.football.footballdata.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Match implements Serializable {

  private int id;
  private Competition competition;
  private Season season;
  private ZonedDateTime utcDate;
  private String status;
  private Object minute;
  private int attendance;
  private String venue;
  private int matchday;
  private String stage;
  private String group;
  private ZonedDateTime lastUpdated;
  private Team homeTeam;
  private Team awayTeam;
  private Score score;
  private List<Goal> goals = new ArrayList<>();
  private List<Booking> bookings = new ArrayList<>();
  private List<Substitution> substitutions = new ArrayList<>();
  private List<Referee> referees = new ArrayList<>();

}
