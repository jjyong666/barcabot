package com.jj.barcabot.tasks;

import com.jj.barcabot.service.football.footballdata.FootballDataService;
import com.jj.barcabot.tasks.config.TaskLogger;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * The type Scheduled tasks.
 */
@Log4j2
@Component
@EnableScheduling
@RequiredArgsConstructor
public class ScheduledTasks {

  private final FootballDataService footballDataService;

  @Scheduled(cron = "${football.football-data.task.games}")
  public void refreshAccessTokens() {
    TaskLogger.logTaskType("next-games");

    footballDataService.getBarcaMatches();
  }

}
