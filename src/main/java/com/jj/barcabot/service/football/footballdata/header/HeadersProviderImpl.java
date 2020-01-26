package com.jj.barcabot.service.football.footballdata.header;

import com.jj.barcabot.service.football.footballdata.config.FootballDataConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HeadersProviderImpl implements HeadersProvider {

  private final FootballDataConfig config;

  @Override
  public HttpEntity<?> getHttpEntity() {
    return buildHttpEntity();
  }

  private HttpEntity<?> buildHttpEntity() {
    return new HttpEntity<>(buildHeaders());
  }

  private HttpHeaders buildHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.add(config.getHeader().getToken(), config.getToken());
    return headers;
  }
}
