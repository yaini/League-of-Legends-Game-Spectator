package com.yaini.batch.job.step;

import com.yaini.batch.job.model.Champion;
import com.yaini.batch.job.processor.ChampionItemProcessor;
import com.yaini.data.entity.ChampionEntity;
import javax.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.json.GsonJsonObjectReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@RequiredArgsConstructor
@Configuration
public class SyncChampionStep {

  public static final String STEP_NAME = "SYNC_CHAMPION_STEP";
  public static final String RESOURCE_PATH = "champion.json";
  public static final int CHUNK_SIZE = 100;

  private final StepBuilderFactory stepBuilderFactory;
  private final ChampionItemProcessor championItemProcessor;
  private final EntityManagerFactory entityManagerFactory;

  @Bean(STEP_NAME)
  @JobScope
  public Step syncChampionStep() {
    return stepBuilderFactory
        .get(STEP_NAME)
        .<Champion, ChampionEntity>chunk(CHUNK_SIZE)
        .reader(championItemReader())
        .processor(championItemProcessor)
        .writer(championItemWriter())
        .build();
  }

  @Bean
  public ItemReader<Champion> championItemReader() {

    return new JsonItemReaderBuilder<Champion>()
        .name(STEP_NAME + "_READER")
        .resource(new ClassPathResource(RESOURCE_PATH))
        .jsonObjectReader(new GsonJsonObjectReader<>(Champion.class))
        .build();
  }

  @Bean
  public ItemWriter<ChampionEntity> championItemWriter() {

    return new JpaItemWriterBuilder<ChampionEntity>()
        .entityManagerFactory(this.entityManagerFactory)
        .usePersist(false)
        .build();
  }
}
