package com.jj.barcabot.controller;

import com.jj.barcabot.service.football.NextGamesService;
import com.jj.barcabot.service.football.footballdata.FootballDataService;
import com.jj.barcabot.service.telegram.sender.ImageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

}
