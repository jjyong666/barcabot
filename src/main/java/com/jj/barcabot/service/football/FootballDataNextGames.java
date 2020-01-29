package com.jj.barcabot.service.football;

import com.jj.barcabot.service.football.dto.NextMatchDto;
import com.jj.barcabot.service.football.footballdata.FootballDataService;
import com.jj.barcabot.service.telegram.MessageSender;
import com.jj.barcabot.service.telegram.config.TelegramConfig;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import net.steppschuh.markdowngenerator.rule.HorizontalRule;
import net.steppschuh.markdowngenerator.table.Table;
import net.steppschuh.markdowngenerator.table.Table.Builder;
import net.steppschuh.markdowngenerator.text.code.CodeBlock;
import net.steppschuh.markdowngenerator.text.emphasis.BoldText;
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

  @Override
  public void publishNextGames() {
    sender.sendMessage(config.getChannelId(), getText(getNextMatches()));
  }

  private String getText(List<NextMatchDto> matches) {
    return new StringBuilder()
        .append(new BoldText("Next Matches")).append("\n")
        .append(new HorizontalRule(55, ' ')).append("\n")
        .append(serializeMatches(matches))
        .toString();
  }

  @SneakyThrows
  private String serializeMatches(List<NextMatchDto> matches) {
    Table.Builder builder = new Builder();
    builder.withAlignments(Table.ALIGN_LEFT, Table.ALIGN_CENTER, Table.ALIGN_RIGHT);
    builder.addRow("Datetime", "Home", "Away");

    matches
        .forEach(x -> builder.addRow(formatDate(x.getUtcDate()), x.getHomeTeam().getName(), x.getAwayTeam().getName()));

    builder.addRow(null, null, null);
    return new CodeBlock(builder.build().serialize(), "Markdown").serialize();
  }

  private String formatDate(ZonedDateTime date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM hh:mm");
    return  date.withZoneSameInstant(ZoneId.of("America/Havana")).format(formatter);
  }

  private List<NextMatchDto> getNextMatches() {
    return dataService.getBarcaMatches()
        .stream()
        .map(x -> modelMapper.map(x, NextMatchDto.class))
        .collect(Collectors.toList());
  }

}
