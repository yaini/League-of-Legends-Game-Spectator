package com.yaini.batch.job.processor;

import com.yaini.batch.job.converter.GameConverter;
import com.yaini.batch.job.model.Game;
import com.yaini.batch.job.parameter.SpectatingJobParameter;
import com.yaini.data.entity.GameEntity;
import com.yaini.data.repository.SummonerGameRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SpectateGameItemProcessor implements ItemProcessor<Game, GameEntity> {

  private final SummonerGameRelationRepository repository;
  private final SpectatingJobParameter jobParameter;

  @Override
  public GameEntity process(final Game item) throws Exception {
    if (repository.existsBySummonerIdAndGameId(item.getSummonerId(), item.getId())) {
      return null;
    }

    return GameConverter.to(item);
  }
}
