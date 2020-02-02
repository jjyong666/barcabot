package com.jj.barcabot.interceptor.config;

import com.jj.barcabot.interceptor.WebhookValidatorInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The type Interceptor config.
 */
@Configuration
@RequiredArgsConstructor
public class InterceptorsConfig implements WebMvcConfigurer {

  private final WebhookValidatorInterceptor webhookValidatorInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    registry.addInterceptor(webhookValidatorInterceptor)
        .addPathPatterns("/updates/**");
  }

}
