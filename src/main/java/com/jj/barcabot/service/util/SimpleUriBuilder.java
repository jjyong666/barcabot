package com.jj.barcabot.service.util;

import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * The type Simple Uri builder.
 */
public class SimpleUriBuilder {

  private static final String SCHEME = "https";

  private SimpleUriBuilder() {}

  /**
   * Gets url.
   *
   * @param baseUrl the base url without the schema
   * @param endpoint the endpoint
   * @return the url
   */
  public static String getUrl(String baseUrl, String endpoint) {
    return getUrl(baseUrl, endpoint, null);
  }

  /**
   * Gets url.
   *
   * @param baseUrl the base url without the schema
   * @param endpoint the endpoint
   * @param queryParams the query params
   * @return the url
   */
  public static String getUrl(String baseUrl, String endpoint, MultiValueMap<String, String> queryParams) {
    return UriComponentsBuilder
        .newInstance()
        .scheme(SCHEME)
        .host(baseUrl)
        .path(endpoint)
        .replaceQueryParams(queryParams)
        .toUriString();
  }

}
