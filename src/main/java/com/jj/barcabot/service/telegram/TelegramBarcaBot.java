package com.jj.barcabot.service.telegram;

import com.jj.barcabot.service.telegram.config.BarcaBotConfig;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Log4j2
@Component
@RequiredArgsConstructor
public class TelegramBarcaBot extends TelegramLongPollingBot {

  private final BarcaBotConfig config;

  @Override
  public String getBotUsername() {
    return config.getUsername();
  }

  @Override
  public String getBotToken() {
    return config.getToken();
  }

  @SneakyThrows
  @PostConstruct
  public void registerBot() {
    log.info("Registering Bot");
    TelegramBotsApi botsApi = new TelegramBotsApi();
    botsApi.registerBot(this);
  }

  @Override
  @SneakyThrows
  public void onUpdateReceived(Update update) {
    processUpdate(update);
  }

  private void processUpdate(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {
      sendMessage(getChatId(update), getText(update));
    }
  }

  private Long getChatId(Update update) {
    return update.getMessage().getChatId();
  }

  private String getText(Update update) {
    return update.getMessage().getText();
  }

  @SneakyThrows
  private synchronized void sendMessage(long chatId, String text) {
    execute(buildMessage(chatId, text));
  }

  private SendMessage buildMessage(long chatId, String text) {
    SendMessage message = new SendMessage();
    message.enableMarkdown(true);
    message.setChatId(chatId);
    message.setText(text);
    return message;
  }


}
