package com.yaini.batch.job.step;

import com.yaini.batch.domain.model.GameQueue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.GsonJsonObjectReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SyncGameQueueStep {

  public static final String STEP_NAME = "SYNC_GAME_QUEUE_STEP";
  public static final String RESOURCE_PATH = "queues.json";
  public static final int CHUNK_SIZE = 10;

  private final StepBuilderFactory stepBuilderFactory;

  @Bean(STEP_NAME)
  @JobScope
  public Step initializeJobInfoStep() {
    return stepBuilderFactory
        .get(STEP_NAME)
        .<GameQueue, GameQueue>chunk(CHUNK_SIZE)
        .reader(gameQueueItemReader())
        .writer(tempItemWriter())
        .build();
  }

  public ItemReader<GameQueue> gameQueueItemReader() {

    return new JsonItemReaderBuilder<GameQueue>()
        .name(STEP_NAME + "_READER")
        .resource(new ClassPathResource(RESOURCE_PATH))
        .jsonObjectReader(new GsonJsonObjectReader<>(GameQueue.class))
        .build();
  }

  public ItemWriter<GameQueue> tempItemWriter() {

    return items -> items.forEach(i -> log.info("Received the information of a customer: {}", i));
  }
}
