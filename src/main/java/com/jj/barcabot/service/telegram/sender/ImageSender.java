package com.jj.barcabot.service.telegram.sender;

import java.io.File;

public interface ImageSender {

  void fromHtml(String html);

  void fromHtml(String html, String chatId);

  void fromFile(File file);

  void fromFile(File file, String chatId);

}
