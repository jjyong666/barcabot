package com.jj.barcabot.controller;

import com.jj.barcabot.service.football.NextGamesService;
import com.jj.barcabot.service.football.footballdata.FootballDataService;
import com.jj.barcabot.service.telegram.sender.ImageSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

@Log4j2
@RestController
@RequiredArgsConstructor
public class IndexController {

  private final FootballDataService service;
  private final NextGamesService service2;
  private final ImageSender imageSender;

  @GetMapping("/players/{id}")
  public ResponseEntity<?> index(@PathVariable String id) {
    return service.getPlayer(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.noContent().build());
  }

  @GetMapping("/matches")
  public void index() {
    imageSender.fromHtml(service2.getNextMatchesHtml());
  }

  @PostMapping("/updates/{token}")
  public void processUpdate(
      @PathVariable("token") String token,
      @RequestBody Update update) {

    log.info("Update: {}", update);
  }

}
