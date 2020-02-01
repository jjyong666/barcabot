package com.jj.barcabot.service.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

public interface TelegramSender {

  void send(SendPhoto photo);

  void send(SendMessage message);

}
