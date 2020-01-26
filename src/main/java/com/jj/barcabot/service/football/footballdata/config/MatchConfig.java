package com.jj.barcabot.service.football.footballdata.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * The type Match config.
 */
@Data
@Component
@ConfigurationProperties("football.football-data.endpoint.match")
public class MatchConfig {

  private String endpoint;

}
