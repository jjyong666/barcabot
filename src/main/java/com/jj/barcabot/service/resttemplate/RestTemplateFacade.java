package com.jj.barcabot.service.resttemplate;

import java.util.Optional;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;

/**
 * The interface Rest template facade.
 */
public interface RestTemplateFacade {

  /**
   * Get optional.
   *
   * @param <T> the type parameter
   * @param url the url
   * @param type the type
   * @param params the params
   * @return the optional
   */
  <T> Optional<T> get(String url, ParameterizedTypeReference<T> type, String... params);

  /**
   * Get optional.
   *
   * @param <T> the type parameter
   * @param url the url
   * @param entity the entity
   * @param type the type
   * @param params the params
   * @return the optional
   */
  <T> Optional<T> get(String url, HttpEntity<?> entity, ParameterizedTypeReference<T> type, String... params);

  /**
   * Deletes a resource.
   *
   * @param url the url
   * @param params the params
   */
  void delete(String url, String... params);

  /**
   * Post optional.
   *
   * @param <T> the type parameter
   * @param url the url
   * @param entity the entity
   * @param type the type
   * @param params the params
   * @return the optional
   */
  <T> Optional<T> post(String url, Object entity, ParameterizedTypeReference<T> type, String... params);

  /**
   * Post optional.
   *
   * @param <T> the type parameter
   * @param url the url
   * @param entity the entity
   * @param type the type
   * @param params the params
   * @return the optional
   */
  <T> Optional<T> post(String url, HttpEntity<?> entity, ParameterizedTypeReference<T> type, String... params);

  /**
   * Put optional.
   *
   * @param <T> the type parameter
   * @param url the url
   * @param entity the entity
   * @param type the type
   * @param params the params
   * @return the optional
   */
  <T> Optional<T> put(String url, Object entity, ParameterizedTypeReference<T> type, String... params);

  /**
   * Put optional.
   *
   * @param <T> the type parameter
   * @param url the url
   * @param entity the entity
   * @param type the type
   * @param params the params
   * @return the optional
   */
  <T> Optional<T> put(String url, HttpEntity<?> entity, ParameterizedTypeReference<T> type, String... params);
}
