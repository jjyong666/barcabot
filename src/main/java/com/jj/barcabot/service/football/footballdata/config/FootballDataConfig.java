package com.jj.barcabot.service.football.footballdata.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * The type Football data config.
 */
@Data
@Component
@ConfigurationProperties("football.football-data")
public class FootballDataConfig {

  private String baseUrl;
  private String token;
  private Header header = new Header();
  private Filter filter = new Filter();

  private final CompetitionConfig competition;
  private final MatchConfig match;
  private final PlayerConfig player;
  private final TeamConfig team;

  @Getter
  @Setter
  public static class Header {

    private String token;
    private String client;
    private String secondsResetCounter;
    private String requestsLeft;

  }

  @Getter
  @Setter
  public static class Filter {

    private String dateFrom;
    private String dateTo;
    private String competitionsIds;
    private String limit;

  }

  @Getter
  @Setter
  public static class Endpoint {

    private String areas;
    private String matches;

  }

}
