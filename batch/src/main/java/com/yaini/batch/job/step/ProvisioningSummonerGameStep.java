package com.yaini.batch.job.step;

import com.yaini.batch.job.converter.SummonerGameConverter;
import com.yaini.batch.job.model.SummonerGame;
import com.yaini.batch.job.reader.ProvisioningSummonerGameItemReader;
import com.yaini.data.entity.SummonerGameRelationEntity;
import javax.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ProvisioningSummonerGameStep {

  public static final String STEP_NAME = "PROVISIONING_SUMMONER_GAME_STEP";
  public static final int CHUNK_SIZE = 100;

  private final StepBuilderFactory stepBuilderFactory;
  private final ProvisioningSummonerGameItemReader provisioningSummonerGameItemReader;
  private final EntityManagerFactory entityManagerFactory;

  @Bean(STEP_NAME)
  @JobScope
  public Step syncGameStep() {
    return stepBuilderFactory
        .get(STEP_NAME)
        .<SummonerGame, SummonerGameRelationEntity>chunk(CHUNK_SIZE)
        .reader(provisioningSummonerGameItemReader)
        .processor(provisioningSummonerGameItemProcessor())
        .writer(provisioningSummonerGameItemWriter())
        .build();
  }

  @Bean
  @StepScope
  public ItemProcessor<SummonerGame, SummonerGameRelationEntity>
      provisioningSummonerGameItemProcessor() {

    return item -> SummonerGameConverter.to(item).withNotice(true);
  }

  @Bean
  @StepScope
  public ItemWriter<SummonerGameRelationEntity> provisioningSummonerGameItemWriter() {

    return new JpaItemWriterBuilder<SummonerGameRelationEntity>()
        .entityManagerFactory(this.entityManagerFactory)
        .build();
  }
}
