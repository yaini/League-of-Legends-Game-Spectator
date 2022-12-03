package com.yaini.batch.job.processor;

import com.yaini.batch.job.converter.ChampionConverter;
import com.yaini.batch.job.model.Champion;
import com.yaini.data.entity.ChampionEntity;
import com.yaini.data.repository.ChampionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChampionItemProcessor implements ItemProcessor<Champion, ChampionEntity> {

  private final ChampionRepository repository;

  @Override
  public ChampionEntity process(final Champion item) throws Exception {
    if (repository.existsById(Long.parseLong(item.getKey()))) {
      return null;
    }

    return ChampionConverter.to(item);
  }
}
