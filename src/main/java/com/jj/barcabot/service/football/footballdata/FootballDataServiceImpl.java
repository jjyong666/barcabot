package com.jj.barcabot.service.football.footballdata;

import com.jj.barcabot.service.football.footballdata.config.FootballDataConfig;
import com.jj.barcabot.service.football.footballdata.dto.Match;
import com.jj.barcabot.service.football.footballdata.dto.Matches;
import com.jj.barcabot.service.football.footballdata.dto.Player;
import com.jj.barcabot.service.football.footballdata.header.HeadersProvider;
import com.jj.barcabot.service.resttemplate.RestTemplateFacade;
import com.jj.barcabot.service.util.SimpleUriBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class FootballDataServiceImpl implements FootballDataService {

  private final RestTemplateFacade restTemplate;
  private final FootballDataConfig config;
  private final HeadersProvider headersProvider;

  @Override
  public Optional<Player> getPlayer(String id) {
    return restTemplate.get(
        SimpleUriBuilder.getUrl(config.getBaseUrl(), config.getPlayer().getEndpoint()),
        headersProvider.getHttpEntity(),
        new ParameterizedTypeReference<>() {},
        id);
  }

  @Override
  public List<Match> getBarcaMatches() {
    return restTemplate.get(
        SimpleUriBuilder.getUrl(config.getBaseUrl(), config.getTeam().getMatches(), getQueryParams()),
        headersProvider.getHttpEntity(),
        new ParameterizedTypeReference<Matches>() {},
        config.getTeam().getFavorite().getBarca())
        .map(Matches::getMatches)
        .orElseGet(ArrayList::new);
  }

  private MultiValueMap<String, String> getQueryParams() {
    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
    queryParams.add(config.getFilter().getDateFrom(), LocalDate.now().toString());
    queryParams.add(config.getFilter().getDateTo(), LocalDate.now().plusMonths(1).toString());
    return queryParams;
  }

}
