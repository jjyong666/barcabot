package com.jj.barcabot.service.football.footballdata.header;

import org.springframework.http.HttpEntity;

public interface HeadersProvider {

  HttpEntity<?> getHttpEntity();
}
