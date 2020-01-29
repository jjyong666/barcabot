package com.jj.barcabot.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Model mapper provider.
 */
@Configuration
public class ModelMapperProvider {

  /**
   * Gets model mapper.
   *
   * @return the model mapper
   */
  @Bean
  public ModelMapper getModelMapper() {
    return new ModelMapper();
  }
}
