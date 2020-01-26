package com.jj.barcabot.service.resttemplate;

import com.jj.barcabot.service.resttemplate.config.error.ResponseExceptionHandler;
import com.jj.barcabot.service.resttemplate.config.error.ResponseExceptionHandlerImpl;
import com.jj.barcabot.service.resttemplate.config.error.RestTemplateResponseErrorHandler;
import com.jj.barcabot.service.resttemplate.config.interceptor.RequestLoggerInterceptor;
import java.util.Collections;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * The type Rest Template Provider config.
 */
@Log4j2
@Configuration
@ComponentScan
public class RestTemplateProvider {

  @Bean
  public ResponseExceptionHandler getErrorResponseHandler() {
    return new ResponseExceptionHandlerImpl();
  }

  /**
   * Gets response error handler.
   *
   * @param errorResponseHandler the error response handler
   * @return the response error handler
   */
  @Bean
  public ResponseErrorHandler getResponseErrorHandler(ResponseExceptionHandler errorResponseHandler) {
    return new RestTemplateResponseErrorHandler(errorResponseHandler);
  }

  /**
   * HttpClient 4 based RestTemplate.
   *
   * @param httpClient HttpClient v4
   * @param errorHandler Response error handler
   * @return the RestTemplate
   */
  @Bean
  @Primary
  public RestTemplate restTemplate(HttpClient httpClient, ResponseErrorHandler errorHandler, RequestLoggerInterceptor loggerInterceptor) {
    return buildRestTemplate(httpClient, errorHandler, loggerInterceptor);
  }

  private RestTemplate buildRestTemplate(HttpClient httpClient, ResponseErrorHandler errorHandler, RequestLoggerInterceptor loggerInterceptor) {
    RestTemplate restTemplate = new RestTemplate(buildRequestFactory(httpClient));
    restTemplate.setErrorHandler(errorHandler);
    restTemplate.setInterceptors(Collections.singletonList(loggerInterceptor));
    return restTemplate;
  }

  private ClientHttpRequestFactory buildRequestFactory(HttpClient httpClient) {
    HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
    httpRequestFactory.setHttpClient(httpClient);
    return httpRequestFactory;
  }

}
