package com.yaini.batch.domain.usecase;

import com.yaini.batch.client.web.RiotWebClient;
import com.yaini.batch.client.web.vo.CurrentGameInfoResponse;
import com.yaini.batch.domain.query.GetAllActiveGameQuery;
import com.yaini.batch.job.converter.GameConverter;
import com.yaini.batch.job.model.Game;
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

    List<String> ids = query.getSummonerIds();
    List<Game> games = new ArrayList<>();

    for (String id : ids) {
      CurrentGameInfoResponse response = webClient.getCurrentGameBySummoner(apiKey, id);

      games.add(GameConverter.from(id, response));
    }

    return games;
  }
}
