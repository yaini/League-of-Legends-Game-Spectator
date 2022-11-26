package com.yaini.batch.job.processor;

import com.yaini.batch.job.converter.GameQueueConverter;
import com.yaini.batch.job.model.GameQueue;
import com.yaini.data.entity.GameQueueEntity;
import com.yaini.data.repository.GameQueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GameQueueProcessor implements ItemProcessor<GameQueue, GameQueueEntity> {

  private final GameQueueRepository repository;

  @Override
  public GameQueueEntity process(final GameQueue item) throws Exception {
    if (repository.existsById(item.getQueueId())) {
      return GameQueueConverter.to(item);
    }

    return null;
  }
}
