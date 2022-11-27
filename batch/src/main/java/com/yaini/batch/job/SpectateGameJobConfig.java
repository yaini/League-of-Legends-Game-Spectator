package com.yaini.batch.job;

import com.yaini.batch.job.step.InitializeSummonerStep;
import com.yaini.batch.job.step.SpectateGameStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpectateGameJobConfig {

  public static final String JOB_NAME = "SPECTATE_GAME_JOB";

  private final JobBuilderFactory jobBuilderFactory;
  private final Step initializeSummonerStep;
  private final Step spectateGameStep;

  public SpectateGameJobConfig(
      final JobBuilderFactory jobBuilderFactory,
      final @Qualifier(InitializeSummonerStep.STEP_NAME) Step initializeSummonerStep,
      final @Qualifier(SpectateGameStep.STEP_NAME) Step spectateGameStep) {
    this.jobBuilderFactory = jobBuilderFactory;
    this.initializeSummonerStep = initializeSummonerStep;
    this.spectateGameStep = spectateGameStep;
  }

  @Bean(JOB_NAME)
  public Job batchJob() {

    return jobBuilderFactory
        .get(JOB_NAME)
        .incrementer(new RunIdIncrementer())
        .start(initializeSummonerStep)
        // .next(spectateGameStep)
        .build();
  }
}