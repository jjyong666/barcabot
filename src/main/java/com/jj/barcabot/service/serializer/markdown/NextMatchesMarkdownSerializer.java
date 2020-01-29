package com.jj.barcabot.service.serializer.markdown;

import com.jj.barcabot.service.football.dto.NextMatchDto;
import com.jj.barcabot.service.serializer.NextMatchesSerializer;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.steppschuh.markdowngenerator.rule.HorizontalRule;
import net.steppschuh.markdowngenerator.table.Table;
import net.steppschuh.markdowngenerator.table.Table.Builder;
import net.steppschuh.markdowngenerator.text.code.CodeBlock;
import net.steppschuh.markdowngenerator.text.emphasis.BoldText;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NextMatchesMarkdownSerializer implements NextMatchesSerializer {

  @Override
  public String serialize(List<NextMatchDto> matches) {
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
    return new CodeBlock(builder.build().serialize(), "Markdown")
        .serialize();
  }

  private String formatDate(ZonedDateTime date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM hh:mm");
    return date.withZoneSameInstant(ZoneId.of("America/Havana")).format(formatter);
  }
}
