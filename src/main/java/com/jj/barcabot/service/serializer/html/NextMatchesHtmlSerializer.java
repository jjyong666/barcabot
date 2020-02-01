package com.jj.barcabot.service.serializer.html;

import static j2html.TagCreator.each;
import static j2html.TagCreator.table;
import static j2html.TagCreator.td;
import static j2html.TagCreator.th;
import static j2html.TagCreator.tr;

import com.jj.barcabot.service.football.dto.NextMatchDto;
import com.jj.barcabot.service.serializer.NextMatchesSerializer;
import gui.ava.html.image.generator.HtmlImageGenerator;
import j2html.tags.ContainerTag;
import java.awt.image.BufferedImage;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service("nextMatchesHtmlSerializer")
@RequiredArgsConstructor
public class NextMatchesHtmlSerializer implements NextMatchesSerializer {

  @Override
  public String serialize(List<NextMatchDto> matches) {

    String render = table(
        buildHeaders("Datetime", "Home", "Away", "Competition"),
        each(matches, this::buildRow)
    ).withStyle(
        "border-collapse: collapse; "
            + "text-align: center"
    ).render();
    return render;
  }

  private ContainerTag buildHeaders(String... headers) {
    return tr(
        each(Arrays.asList(headers), this::buildHeader)).withStyle("background-color: #33B5FF;");
  }

  private ContainerTag buildHeader(String header) {
    return th(header).withStyle(
        "padding: 0 15; "
            + "text-align: center; "
    );
  }

  private ContainerTag addBorder(ContainerTag tag) {
    return tag.withStyle(
        "text-align: center; "
    );
  }

  private ContainerTag buildRow(NextMatchDto match) {
    return tr(
        addBorder(td(formatDate(match.getUtcDate()))),
        addBorder(td(match.getHomeTeam().getName())),
        addBorder(td(match.getAwayTeam().getName())),
        addBorder(td(getCompetition(match))));
  }

  private String getCompetition(NextMatchDto match) {
    String matchDay = String.valueOf(match.getMatchDay());
    if (StringUtils.isNotEmpty(match.getStage()) && matchDay.equals("0")) {
      matchDay = match.getStage();
    }

    return match.getCompetition().getName() + " #" + matchDay;
  }

  private String formatDate(ZonedDateTime date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM hh:mm");
    return date.withZoneSameInstant(ZoneId.of("America/Havana")).format(formatter);
  }
}
