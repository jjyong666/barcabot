package com.jj.barcabot.service.telegram.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("telegram.api.bot")
public class BarcaBotConfig {

  private String id;
  private String username;
  private String token;
  private String path;

}
