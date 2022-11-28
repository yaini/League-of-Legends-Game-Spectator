package com.yaini.batch.job.step;

import com.yaini.batch.job.model.Game;
import com.yaini.batch.job.processor.SpectateGameItemProcessor;
import com.yaini.batch.job.reader.SpectateGameItemReader;
import com.yaini.data.entity.GameEntity;
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
  public static final int CHUNK_SIZE = 100;

  private final StepBuilderFactory stepBuilderFactory;
  private final SpectateGameItemReader spectateGameItemReader;
  private final SpectateGameItemProcessor spectateGameItemProcessor;

  @Bean(STEP_NAME)
  @JobScope
  public Step initializeJobInfoStep() {
    return stepBuilderFactory
        .get(STEP_NAME)
        .<Game, GameEntity>chunk(CHUNK_SIZE)
        .reader(spectateGameItemReader)
        .processor(spectateGameItemProcessor)
        // save and send
        .writer(items -> items.forEach(System.out::println))
        .build();
  }
}
