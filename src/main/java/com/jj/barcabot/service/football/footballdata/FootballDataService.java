package com.jj.barcabot.service.football.footballdata;

import com.jj.barcabot.service.football.footballdata.dto.Match;
import com.jj.barcabot.service.football.footballdata.dto.Player;
import java.util.List;
import java.util.Optional;

public interface FootballDataService {

  Optional<Player> getPlayer(String id);

  List<Match> getBarcaMatches();
}
