package com.yaini.batch.job.usecase;

import com.yaini.batch.client.web.RiotWebClient;
import com.yaini.batch.client.web.vo.CurrentGameInfoResponse;
import com.yaini.batch.job.converter.GameConverter;
import com.yaini.batch.job.model.Game;
import com.yaini.batch.job.model.Summoner;
import com.yaini.batch.job.usecase.query.GetAllActiveGameQuery;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetAllActiveGameUseCase {

  private final RiotWebClient webClient;

  @Value("${riot.api-key}") private String apiKey;

  public List<Game> execute(final GetAllActiveGameQuery query) {

    List<Game> games = new ArrayList<>();

    for (Summoner summoner : query.getSummoners()) {
      String id = summoner.getId();
      CurrentGameInfoResponse response = webClient.getCurrentGameBySummoner(apiKey, id);

      games.add(GameConverter.from(summoner, response));
    }

    return games;
  }
}
