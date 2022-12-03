package com.yaini.batch.job.step;

import com.yaini.batch.job.parameter.SpectatingJobParameter;
import com.yaini.batch.job.usecase.GetAllGameQueueUseCase;
import com.yaini.batch.job.usecase.query.GetAllGameQueueMapQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class InitializeGameQueueStep {

  public static final String STEP_NAME = "INITIALIZE_GAME_QUEUE_STEP";

  private final StepBuilderFactory stepBuilderFactory;
  private final SpectatingJobParameter parameter;
  private final GetAllGameQueueUseCase useCase;

  @Bean(STEP_NAME)
  @JobScope
  public Step initializeActiveGameStep() {
    return stepBuilderFactory
        .get(STEP_NAME)
        .tasklet(
            (stepContribution, chunkContext) -> {
              parameter.setGameQueues(useCase.execute(new GetAllGameQueueMapQuery()));

              return RepeatStatus.FINISHED;
            })
        .build();
  }
}
