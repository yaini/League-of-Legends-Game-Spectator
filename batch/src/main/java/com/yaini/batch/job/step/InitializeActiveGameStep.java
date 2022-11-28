package com.yaini.batch.job.step;

import com.yaini.batch.domain.query.GetAllActiveGameQuery;
import com.yaini.batch.domain.usecase.GetAllActiveGameUseCase;
import com.yaini.batch.job.parameter.SpectatingJobParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class InitializeActiveGameStep {

  public static final String STEP_NAME = "INITIALIZE_ACTIVE_GAME_STEP";

  private final StepBuilderFactory stepBuilderFactory;
  private final SpectatingJobParameter parameter;
  private final GetAllActiveGameUseCase useCase;

  @Bean(STEP_NAME)
  @JobScope
  public Step initializeJobInfoStep() {
    return stepBuilderFactory
        .get(STEP_NAME)
        .tasklet(
            (stepContribution, chunkContext) -> {
              GetAllActiveGameQuery query = new GetAllActiveGameQuery(parameter.getSummonerIds());

              parameter.setActiveGames(useCase.execute(query));

              return RepeatStatus.FINISHED;
            })
        .build();
  }
}
