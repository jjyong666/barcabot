package com.jj.barcabot.service.telegram.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("telegram.api")
public class TelegramConfig {

  private String creatorId;
  private String botId;
  private String channelId;

}
