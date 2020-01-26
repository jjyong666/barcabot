package com.jj.barcabot.service.resttemplate;

import com.jj.barcabot.exception.CoreRuntimeException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * The class Rest template facade.
 */
@Component
@RequiredArgsConstructor
public class RestTemplateFacadeImpl implements RestTemplateFacade {

  private final RestTemplate restTemplate;

  @Override
  public <T> Optional<T> get(String url, ParameterizedTypeReference<T> type, String... params) {
    return get(url, null, type, params);
  }

  @Override
  public <T> Optional<T> get(String url, HttpEntity<?> entity, ParameterizedTypeReference<T> type, String... params) {
    return extractBody(executeRequest(url, HttpMethod.GET, entity, type, Stream.of(params)));
  }

  private <T> ResponseEntity<T> executeRequest(
      String url,
      HttpMethod method,
      HttpEntity<?> entity,
      ParameterizedTypeReference<T> type,
      Stream<String> params) {

    return restTemplate.exchange(decodeUrl(url), method, entity, type, params.toArray());
  }

  private String decodeUrl(String url) {
    try {
      return URLDecoder.decode(url, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException | IllegalArgumentException e) {
      throw new CoreRuntimeException("Error decoding URL: " + url, e);
    }
  }

  private <T> Optional<T> extractBody(ResponseEntity<T> response) {
    return Optional.of(response)
        .map(HttpEntity::getBody);
  }

  @Override
  public void delete(String url, String... params) {
    restTemplate.delete(decodeUrl(url), Stream.of(params).toArray());
  }

  @Override
  public <T> Optional<T> post(String url, Object entity, ParameterizedTypeReference<T> type, String... params) {
    return post(url, new HttpEntity<>(entity), type, params);
  }

  @Override
  public <T> Optional<T> post(String url, HttpEntity<?> entity, ParameterizedTypeReference<T> type, String... params) {
    return extractBody(executeRequest(url, HttpMethod.POST, entity, type, Stream.of(params)));
  }

  @Override
  public <T> Optional<T> put(String url, Object entity, ParameterizedTypeReference<T> type, String... params) {
    return put(url, new HttpEntity<>(entity), type, params);
  }

  @Override
  public <T> Optional<T> put(String url, HttpEntity<?> entity, ParameterizedTypeReference<T> type, String... params) {
    return extractBody(executeRequest(url, HttpMethod.PUT, entity, type, Stream.of(params)));
  }

}
