package com.jj.barcabot.service.football.footballdata.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("football.football-data.endpoint.competition")
public class CompetitionConfig {

  private String endpoint;
  private String standings;
  private String scorers;
  private String teams;

  private Favorite favorite = new Favorite();

  @Getter
  @Setter
  public static class Favorite {

    private String laLiga;
    private String copa;
    private String supercopa;
    private String premier;
    private String facup;
    private String cup;
    private String communityShield;

  }
}
