package com.jj.barcabot.service.football.footballdata.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("football.football-data.endpoint.player")
public class PlayerConfig {

  private String endpoint;

  private Favorite favorite = new Favorite();

  @Getter
  @Setter
  public static class Favorite {

    private String messi;

  }

}
