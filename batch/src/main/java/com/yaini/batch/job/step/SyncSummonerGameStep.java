package com.yaini.batch.job.step;

import com.yaini.batch.job.model.Game;
import com.yaini.batch.job.processor.SyncSummonerGameItemProcessor;
import com.yaini.batch.job.reader.SyncSummonerGameItemReader;
import com.yaini.data.entity.SummonerGameRelationEntity;
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
public class SyncSummonerGameStep {

  public static final String STEP_NAME = "SYNC_SUMMONER_GAME_STEP";
  public static final int CHUNK_SIZE = 100;

  private final StepBuilderFactory stepBuilderFactory;
  private final SyncSummonerGameItemReader syncSummonerGameItemReader;
  private final SyncSummonerGameItemProcessor syncSummonerGameItemProcessor;
  private final EntityManagerFactory entityManagerFactory;

  @Bean(STEP_NAME)
  @JobScope
  public Step syncGameStep() {
    return stepBuilderFactory
        .get(STEP_NAME)
        .<Game, SummonerGameRelationEntity>chunk(CHUNK_SIZE)
        .reader(syncSummonerGameItemReader)
        .processor(syncSummonerGameItemProcessor)
        .writer(summonerGameItemWriter())
        .build();
  }

  @Bean
  @StepScope
  public ItemWriter<SummonerGameRelationEntity> summonerGameItemWriter() {

    return new JpaItemWriterBuilder<SummonerGameRelationEntity>()
        .entityManagerFactory(this.entityManagerFactory)
        .usePersist(false)
        .build();
  }
}
