package com.yaini.batch.job.processor;

import com.yaini.batch.job.converter.SummonerGameConverter;
import com.yaini.batch.job.model.Game;
import com.yaini.data.entity.SummonerGameRelationEntity;
import com.yaini.data.repository.SummonerGameRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SyncSummonerGameItemProcessor
    implements ItemProcessor<Game, SummonerGameRelationEntity> {

  private final SummonerGameRelationRepository repository;

  @Override
  public SummonerGameRelationEntity process(final Game item) throws Exception {
    if (repository.existsBySummonerIdAndGameId(item.getSummoner().getId(), item.getId())) {
      return null;
    }

    return SummonerGameConverter.to(item);
  }
}
