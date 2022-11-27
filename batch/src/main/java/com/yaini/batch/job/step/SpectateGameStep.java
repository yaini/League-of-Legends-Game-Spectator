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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        // .reader(gameQueueItemReader())
        // 있는지 확인
        // .processor(gameQueueProcessor)
        // 없으면 저장, 발송
        // .writer(tempItemWriter())
        .build();
  }
}
