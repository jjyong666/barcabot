package com.jj.barcabot.service.telegram.sender;

import com.jj.barcabot.service.telegram.TelegramSender;
import com.jj.barcabot.service.telegram.config.TelegramConfig;
import gui.ava.html.image.generator.HtmlImageGenerator;
import java.io.File;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

@Service
@RequiredArgsConstructor
public class ImageSenderImpl implements ImageSender {

  private final TelegramSender sender;
  private final TelegramConfig config;

  @Override
  public void fromHtml(String html) {
    fromHtml(html, config.getChannelId());
  }

  @Override
  public void fromHtml(String html, String chatId) {
    fromFile(convertHtmlToImage(html), chatId);
  }

  private File convertHtmlToImage(String html) {
    HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
    imageGenerator.loadHtml(html);
    imageGenerator.saveAsImage("temp.png");
    return new File("temp.png");
  }

  @Override
  public void fromFile(File file) {
    fromFile(file, config.getChannelId());
  }

  @Override
  public void fromFile(File file, String chatId) {
    sender.send(
        buildPhoto(chatId, file));
  }

  private SendPhoto buildPhoto(String chatId, File image) {
    SendPhoto photo = new SendPhoto();
    photo.setChatId(chatId);
    photo.setPhoto(image);
    return photo;
  }

}
