package com.yaini.batch.job.parameter;

import com.yaini.batch.job.model.Game;
import com.yaini.batch.job.model.Summoner;
import com.yaini.batch.job.model.SummonerGame;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.stereotype.Component;

@Getter
@JobScope
@Component
public class SpectatingJobParameter {
  @Setter private List<Summoner> summoners;
  @Setter private List<Game> activeGames;
  @Setter private Map<Long, String> gameQueues;
  @Setter private Map<Long, String> champions;
  @Setter private List<SummonerGame> summonerGames;
}
