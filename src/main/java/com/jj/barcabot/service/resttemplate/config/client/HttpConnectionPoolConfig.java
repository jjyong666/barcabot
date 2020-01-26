package com.jj.barcabot.service.resttemplate.config.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * The type Http connection pool configuration.
 */
@Data
@Component
@ConfigurationProperties(prefix = "httppool")
public class HttpConnectionPoolConfig {

  private Integer maxPerRoute;
  private Integer maxTotal;
  private Integer connectionRequestTimeout;
  private Integer connectTimeout;
  private Integer socketTimeout;

}
