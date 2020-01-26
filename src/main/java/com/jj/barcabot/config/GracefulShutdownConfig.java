package com.jj.barcabot.config;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;

/**
 * The type Graceful shutdown config.
 */
@Log4j2
@Configuration
public class GracefulShutdownConfig {

  /**
   * Graceful shutdown tomcat graceful shutdown.
   *
   * @return the tomcat graceful shutdown
   */
  @Bean
  public TomcatGracefulShutdown gracefulShutdown() {
    return new TomcatGracefulShutdown();
  }

  /**
   * Web server customizer web server factory customizer.
   *
   * @param tomcatGracefulShutdown the tomcat graceful shutdown
   * @return the web server factory customizer
   */
  @Bean
  public WebServerFactoryCustomizer webServerCustomizer(TomcatGracefulShutdown tomcatGracefulShutdown) {
    return factory -> {
      if (factory instanceof TomcatServletWebServerFactory) {
        ((TomcatServletWebServerFactory) factory).addConnectorCustomizers(tomcatGracefulShutdown);
      }
    };
  }

  private static class TomcatGracefulShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

    private volatile Connector connector;

    @Override
    public void customize(Connector connector) {
      this.connector = connector;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
      log.warn("Received ContextClosedEvent");

      Executor executor = this.connector.getProtocolHandler().getExecutor();
      if (executor instanceof ThreadPoolExecutor) {
        try {
          ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
          threadPoolExecutor.shutdown();
          if (!threadPoolExecutor.awaitTermination(10, TimeUnit.SECONDS)) {
            log.warn("Tomcat thread pool did not shut down gracefully within 10 seconds. Proceeding with forceful shutdown");
          }
        } catch (InterruptedException ex) {
          Thread.currentThread().interrupt();
        }
      }
    }

  }

}
