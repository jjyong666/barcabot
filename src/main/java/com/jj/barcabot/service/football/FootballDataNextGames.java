package com.jj.barcabot.service.football;

import com.jj.barcabot.service.football.dto.NextMatchDto;
import com.jj.barcabot.service.football.footballdata.FootballDataService;
import com.jj.barcabot.service.serializer.NextMatchesSerializer;
import com.jj.barcabot.service.telegram.TelegramSender;
import com.jj.barcabot.service.telegram.config.TelegramConfig;
import gui.ava.html.image.generator.HtmlImageGenerator;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

@Log4j2
@Component
@RequiredArgsConstructor
public class FootballDataNextGames implements NextGamesService {

  private final TelegramSender sender;
  private final FootballDataService dataService;
  private final ModelMapper modelMapper;
  private final TelegramConfig config;
  private final NextMatchesSerializer nextMatchesHtmlSerializer;

  @Override
  public void publishNextGames() {
    sender.send(
        buildPhoto(
            config.getChannelId(),
            convertHtmlToImage(
                nextMatchesHtmlSerializer.serialize(
                    getNextMatches()))));
  }

  private List<NextMatchDto> getNextMatches() {
    return dataService.getBarcaMatches()
        .stream()
        .map(x -> modelMapper.map(x, NextMatchDto.class))
        .collect(Collectors.toList());
  }

  @SneakyThrows
  private SendPhoto buildPhoto(String chatId, File image) {
    SendPhoto photo = new SendPhoto();
    photo.setChatId(chatId);
    photo.setPhoto(image);
    return photo;
  }

  private File convertHtmlToImage(String html) {
    HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
    imageGenerator.loadHtml(html);
    imageGenerator.saveAsImage("temp.png");
    return new File("temp.png");
  }

}
