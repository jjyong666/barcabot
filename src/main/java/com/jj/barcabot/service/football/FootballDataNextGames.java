package com.jj.barcabot.service.football;

import com.jj.barcabot.service.football.dto.NextMatchDto;
import com.jj.barcabot.service.football.footballdata.FootballDataService;
import com.jj.barcabot.service.serializer.NextMatchesSerializer;
import com.jj.barcabot.service.telegram.MessageSender;
import com.jj.barcabot.service.telegram.config.TelegramConfig;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class FootballDataNextGames implements NextGamesService {

  private final MessageSender sender;
  private final FootballDataService dataService;
  private final ModelMapper modelMapper;
  private final TelegramConfig config;
  private final NextMatchesSerializer serializer;

  @Override
  public void publishNextGames() {
    sender.sendMessage(config.getChannelId(), serializer.serialize(getNextMatches()));
  }

  private List<NextMatchDto> getNextMatches() {
    return dataService.getBarcaMatches()
        .stream()
        .map(x -> modelMapper.map(x, NextMatchDto.class))
        .collect(Collectors.toList());
  }

}
