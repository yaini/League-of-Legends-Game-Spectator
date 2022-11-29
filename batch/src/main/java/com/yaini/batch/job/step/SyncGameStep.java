package com.yaini.batch.job.step;

import com.yaini.batch.job.model.Game;
import com.yaini.batch.job.processor.SyncGameItemProcessor;
import com.yaini.batch.job.reader.SyncGameItemReader;
import com.yaini.data.entity.GameEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class SyncGameStep {

  public static final String STEP_NAME = "SYNC_GAME_STEP";
  public static final int CHUNK_SIZE = 100;

  private final StepBuilderFactory stepBuilderFactory;
  private final SyncGameItemReader syncGameItemReader;
  private final SyncGameItemProcessor syncGameItemProcessor;

  @Bean(STEP_NAME)
  @JobScope
  public Step syncGameStep() {
    return stepBuilderFactory
        .get(STEP_NAME)
        .<Game, GameEntity>chunk(CHUNK_SIZE)
        .reader(syncGameItemReader)
        .processor(syncGameItemProcessor)
        .writer(items -> items.forEach(System.out::println))
        .build();
  }
}
