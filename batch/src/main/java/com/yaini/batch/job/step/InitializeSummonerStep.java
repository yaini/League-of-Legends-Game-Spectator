package com.yaini.batch.job.step;

import com.yaini.batch.domain.query.GetAllSummonerNameQuery;
import com.yaini.batch.domain.usecase.GetAllSummonerUseCase;
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
public class InitializeSummonerStep {

  public static final String STEP_NAME = "INITIALIZE_SUMMONER_STEP";

  private final StepBuilderFactory stepBuilderFactory;
  private final SpectatingJobParameter parameter;
  private final GetAllSummonerUseCase useCase;

  @Bean(STEP_NAME)
  @JobScope
  public Step initializeJobInfoStep() {
    return stepBuilderFactory
        .get(STEP_NAME)
        .tasklet(
            (stepContribution, chunkContext) -> {
              parameter.setSummonerNames(useCase.execute(new GetAllSummonerNameQuery()));

              return RepeatStatus.FINISHED;
            })
        .build();
  }
}
