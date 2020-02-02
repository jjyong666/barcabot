package com.jj.barcabot.tasks;

import com.jj.barcabot.service.football.NextGamesService;
import com.jj.barcabot.service.telegram.sender.ImageSender;
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

  private final NextGamesService nextGamesService;
  private final ImageSender sender;

  @Scheduled(cron = "${football.football-data.task.games}")
  public void refreshAccessTokens() {
    TaskLogger.logTaskType("next-games");

    sender.fromHtml(nextGamesService.getNextMatchesHtml());
  }

}
