package com.jj.barcabot.service.telegram;

import com.jj.barcabot.service.telegram.config.BarcaBotConfig;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;

@Log4j2
@Component
@RequiredArgsConstructor
public class BarcaBot extends TelegramWebhookBot implements TelegramSender {

  private final BarcaBotConfig config;

  @Override
  public BotApiMethod onWebhookUpdateReceived(Update update) {
    log.info("Update: {}", update);
    return null;
  }

  @Override
  public String getBotUsername() {
    return config.getUsername();
  }

  @Override
  public String getBotToken() {
    return config.getToken();
  }

  @Override
  public String getBotPath() {
    return "/updates/" + config.getToken();
  }

  @SneakyThrows
  @PostConstruct
  public void registerBot() {
    log.info("Registering Bot");
    TelegramBotsApi botsApi = new TelegramBotsApi();
    botsApi.registerBot(this);
  }

  public void onUpdateReceived(Update update) {
    processUpdate(update);
  }

  private void processUpdate(Update update) {
    log.debug("Update received: {}", update);
  }

  @Override
  @SneakyThrows
  public synchronized void send(SendPhoto photo) {
    execute(photo);
  }

  @Override
  @SneakyThrows
  public synchronized void send(SendMessage message) {
    execute(message);
  }


}
