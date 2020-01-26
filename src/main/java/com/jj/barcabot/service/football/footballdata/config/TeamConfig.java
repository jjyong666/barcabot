package com.jj.barcabot.service.football.footballdata.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("football.football-data.endpoint.team")
public class TeamConfig {

  private String endpoint;
  private String matches;

  private Favorite favorite = new Favorite();

  @Getter
  @Setter
  public static class Favorite {

    private String barca;
    private String city;

  }

}
