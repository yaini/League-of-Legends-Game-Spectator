package com.yaini.batch.job.processor;

import com.yaini.batch.job.converter.GameConverter;
import com.yaini.batch.job.model.Game;
import com.yaini.data.entity.GameEntity;
import com.yaini.data.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SyncGameItemProcessor implements ItemProcessor<Game, GameEntity> {

  private final GameRepository repository;

  @Override
  public GameEntity process(final Game item) throws Exception {
    if (repository.existsById(item.getId())) {
      return null;
    }

    return GameConverter.to(item);
  }
}
