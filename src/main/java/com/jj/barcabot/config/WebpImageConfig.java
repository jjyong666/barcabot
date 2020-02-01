package com.jj.barcabot.config;

import com.jj.barcabot.service.util.ProcessHelper;
import java.io.File;
import java.net.URL;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;

@Log4j2
@Configuration
@RequiredArgsConstructor
@Profile(ProfilesConfig.PRODUCTION)
public class WebpImageConfig implements ApplicationListener<ContextRefreshedEvent> {

  private static final String CWEBP_URL = "https://dl.bintray.com/jjyong666/cwebp/net/jj/cwebp/1.1.0-linux/cwebp";
  private static final String CWEBP_PATH = "bin/cwebp";

  private final ProcessHelper processHelper;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {

    downloadCwebpBinary();
    addExecutionPermissions();
  }

  @SneakyThrows
  private void downloadCwebpBinary() {
    log.debug("Downloading cwebp binary ...");

    FileUtils.copyURLToFile(
        new URL(CWEBP_URL),
        new File(CWEBP_PATH));

    addExecutionPermissions();
  }

  private void addExecutionPermissions() {
    int exitCode = processHelper.executeAndWait("chmod +x " + CWEBP_PATH);
    if(exitCode != 0) {
      log.error("Error assigning execution permissions to the cwebp binary");
    }
  }
}
