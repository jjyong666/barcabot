package com.jj.barcabot.config;

import static java.util.Collections.singletonList;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Provide custom Jackson configuration.
 */
@Log4j2
@Configuration
public class JacksonConfiguration {

  /**
   * Mapping jackson 2 http message converter mapping jackson 2 http message converter.
   *
   * @return the mapping jackson 2 http message converter
   */
  @Bean
  @Primary
  public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
    log.debug("Providing custom MappingJackson2HttpMessageConverter");
    MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
    jacksonConverter.setSupportedMediaTypes(singletonList(MediaType.APPLICATION_JSON));
    jacksonConverter.setObjectMapper(objectMapper());
    return jacksonConverter;
  }

  /**
   * Object mapper object mapper.
   *
   * @return the object mapper
   */
  @Bean
  @Primary
  public ObjectMapper objectMapper() {
    log.debug("Providing custom Jackson Object Mapper");
    return new ObjectMapper()
        .setSerializationInclusion(Include.ALWAYS)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false)
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
        .registerModule(new JavaTimeModule());
  }
}
