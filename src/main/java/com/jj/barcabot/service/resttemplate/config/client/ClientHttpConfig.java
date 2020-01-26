package com.jj.barcabot.service.resttemplate.config.client;

import lombok.RequiredArgsConstructor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Client http pool configuration.
 */
@Configuration
@RequiredArgsConstructor
public class ClientHttpConfig {

  private final HttpConnectionPoolConfig poolConfiguration;

  /**
   * Http client closeable http client.
   *
   * @return the closeable http client
   */
  @Bean
  public CloseableHttpClient httpClient() {
    return HttpClientBuilder
        .create()
        .setConnectionManager(buildConnectionManager())
        .setDefaultRequestConfig(buildRequestConfig())
        .useSystemProperties()
        .disableRedirectHandling()
        .build();
  }

  private RequestConfig buildRequestConfig() {
    return RequestConfig
        .custom()
        .setConnectionRequestTimeout(poolConfiguration.getConnectionRequestTimeout())
        .setConnectTimeout(poolConfiguration.getConnectTimeout())
        .setSocketTimeout(poolConfiguration.getSocketTimeout())
        .build();
  }

  private PoolingHttpClientConnectionManager buildConnectionManager() {
    PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
    connManager.setDefaultMaxPerRoute(poolConfiguration.getMaxPerRoute());
    connManager.setMaxTotal(poolConfiguration.getMaxTotal());
    return connManager;
  }

}
