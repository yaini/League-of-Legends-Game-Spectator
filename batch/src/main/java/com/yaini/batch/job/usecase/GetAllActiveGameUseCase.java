package com.yaini.batch.job.usecase;

import com.yaini.batch.client.web.RiotWebClient;
import com.yaini.batch.client.web.support.exception.ResourceNotFoundException;
import com.yaini.batch.client.web.vo.CurrentGameInfoResponse;
import com.yaini.batch.job.converter.GameConverter;
import com.yaini.batch.job.model.Game;
import com.yaini.batch.job.model.Summoner;
import com.yaini.batch.job.usecase.query.GetAllActiveGameQuery;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetAllActiveGameUseCase {

  private final RiotWebClient webClient;

  @Value("${riot.api-key}") private String apiKey;

  public List<Game> execute(final GetAllActiveGameQuery query) {

    List<Game> games = new ArrayList<>();

    for (Summoner summoner : query.getSummoners()) {
      Game game = this.getCurrentGame(summoner.getId());

      if (game != null) {
        games.add(game);
      }
    }

    return games;
  }

  private Game getCurrentGame(final String summonerId) {
    try {
      CurrentGameInfoResponse response = webClient.getCurrentGameBySummoner(apiKey, summonerId);

      return GameConverter.from(response, summonerId);
    } catch (ResourceNotFoundException e) {
      log.debug("Not Found {}'s Current Game", summonerId);

      return null;
    }
  }
}
