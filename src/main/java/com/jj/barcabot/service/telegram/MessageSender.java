package com.jj.barcabot.service.telegram;

public interface MessageSender {

  void sendMessage(String chatId, String text);

}
