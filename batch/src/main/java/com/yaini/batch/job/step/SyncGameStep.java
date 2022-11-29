package com.yaini.batch.job.step;

import com.yaini.batch.job.model.Game;
import com.yaini.batch.job.processor.SyncGameItemProcessor;
import com.yaini.batch.job.reader.SyncGameItemReader;
import com.yaini.data.entity.GameEntity;
import javax.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
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
  private final EntityManagerFactory entityManagerFactory;

  @Bean(STEP_NAME)
  @JobScope
  public Step syncGameStep() {
    return stepBuilderFactory
        .get(STEP_NAME)
        .<Game, GameEntity>chunk(CHUNK_SIZE)
        .reader(syncGameItemReader)
        .processor(syncGameItemProcessor)
        .writer(gameItemWriter())
        .build();
  }

  @Bean
  @StepScope
  public ItemWriter<GameEntity> gameItemWriter() {

    return new JpaItemWriterBuilder<GameEntity>()
        .entityManagerFactory(this.entityManagerFactory)
        .usePersist(false)
        .build();
  }
}
