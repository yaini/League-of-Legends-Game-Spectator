package com.yaini.batch.job.step;

import com.yaini.batch.job.parameter.SpectatingJobParameter;
import com.yaini.batch.job.usecase.GetAllSummonerUseCase;
import com.yaini.batch.job.usecase.query.GetAllSummonerNameQuery;
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
  public Step initializeSummonerStep() {
    return stepBuilderFactory
        .get(STEP_NAME)
        .tasklet(
            (stepContribution, chunkContext) -> {
              parameter.setSummoners(useCase.execute(new GetAllSummonerNameQuery()));

              return RepeatStatus.FINISHED;
            })
        .build();
  }
}
