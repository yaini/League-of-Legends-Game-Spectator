package com.yaini.batch.job.step;

import com.yaini.batch.job.parameter.SpectatingJobParameter;
import com.yaini.batch.job.usecase.GetAllChampionUseCase;
import com.yaini.batch.job.usecase.query.GetAllChampionNameQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class InitializeChampionStep {

  public static final String STEP_NAME = "INITIALIZE_CHAMPION_STEP";

  private final StepBuilderFactory stepBuilderFactory;
  private final SpectatingJobParameter parameter;
  private final GetAllChampionUseCase useCase;

  @Bean(STEP_NAME)
  @JobScope
  public Step initializeActiveGameStep() {
    return stepBuilderFactory
        .get(STEP_NAME)
        .tasklet(
            (stepContribution, chunkContext) -> {
              parameter.setChampions(useCase.execute(new GetAllChampionNameQuery()));

              return RepeatStatus.FINISHED;
            })
        .build();
  }
}
