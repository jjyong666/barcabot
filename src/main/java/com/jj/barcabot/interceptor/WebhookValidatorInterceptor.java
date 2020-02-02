package com.jj.barcabot.interceptor;

import com.jj.barcabot.exception.AuthenticationException;
import com.jj.barcabot.service.telegram.config.BarcaBotConfig;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Log4j2
@Component
@RequiredArgsConstructor
public class WebhookValidatorInterceptor extends HandlerInterceptorAdapter {

  static final String TOKEN_PARAM = "token";

  private final BarcaBotConfig config;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    log.debug("Validating webhook");

    if (!config.getToken().equals(extractPathParam(request))) {
      throw new AuthenticationException("I don't know you");
    }
    return true;
  }

  private String extractPathParam(HttpServletRequest request) {
    return Optional.ofNullable(request)
        .map(x -> x.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE))
        .map(x -> (Map) x)
        .map(x -> x.get(WebhookValidatorInterceptor.TOKEN_PARAM))
        .map(Object::toString)
        .orElse(null);
  }

}
