package com.yaini.batch.job.step;

import com.yaini.batch.job.model.GameQueue;
import com.yaini.batch.job.parameter.SpectatingJobParameter;
import com.yaini.batch.job.processor.GameQueueProcessor;
import com.yaini.data.entity.GameQueueEntity;
import javax.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
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
public class SpectateGameStep {

  public static final String STEP_NAME = "SPECTATE_GAME_STEP";
  public static final String RESOURCE_PATH = "queues.json";
  public static final int CHUNK_SIZE = 100;

  private final StepBuilderFactory stepBuilderFactory;
  private final SpectatingJobParameter parameter;
  private final GameQueueProcessor gameQueueProcessor;
  private final EntityManagerFactory entityManagerFactory;

  @Bean(STEP_NAME)
  @JobScope
  public Step initializeJobInfoStep() {
    return stepBuilderFactory
        .get(STEP_NAME)
        .<GameQueue, GameQueueEntity>chunk(CHUNK_SIZE)
        // API로 가져옴
        .reader(gameQueueItemReader())
        // 있는지 확인
        .processor(gameQueueProcessor)
        // 없으면 저장, 발송
        .writer(tempItemWriter())
        .build();
  }

  @Bean
  @StepScope
  public ItemReader<GameQueue> gameQueueItemReader() {

    return new JsonItemReaderBuilder<GameQueue>()
        .name(STEP_NAME + "_READER")
        .resource(new ClassPathResource(RESOURCE_PATH))
        .jsonObjectReader(new GsonJsonObjectReader<>(GameQueue.class))
        .build();
  }

  @Bean
  @StepScope
  public ItemWriter<GameQueueEntity> tempItemWriter() {

    return new JpaItemWriterBuilder<GameQueueEntity>()
        .entityManagerFactory(this.entityManagerFactory)
        .usePersist(false)
        .build();
  }
}
