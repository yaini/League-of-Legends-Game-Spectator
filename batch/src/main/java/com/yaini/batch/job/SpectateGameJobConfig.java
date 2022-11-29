package com.yaini.batch.job;

import com.yaini.batch.job.step.InitializeActiveGameStep;
import com.yaini.batch.job.step.InitializeSummonerStep;
import com.yaini.batch.job.step.SyncGameStep;
import com.yaini.batch.job.step.SyncSummonerGameStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(
    prefix = "spring.batch.job",
    value = "names",
    havingValue = SpectateGameJobConfig.JOB_NAME)
public class SpectateGameJobConfig {

  public static final String JOB_NAME = "SPECTATE_GAME_JOB";

  private final JobBuilderFactory jobBuilderFactory;
  private final Step initializeSummonerStep;
  private final Step initializeActiveGameStep;
  private final Step syncGameStep;
  private final Step syncSummonerGameStep;

  public SpectateGameJobConfig(
      final JobBuilderFactory jobBuilderFactory,
      final @Qualifier(InitializeSummonerStep.STEP_NAME) Step initializeSummonerStep,
      final @Qualifier(InitializeActiveGameStep.STEP_NAME) Step initializeActiveGameStep,
      final @Qualifier(SyncGameStep.STEP_NAME) Step syncGameStep,
      final @Qualifier(SyncSummonerGameStep.STEP_NAME) Step syncSummonerGameStep) {
    this.jobBuilderFactory = jobBuilderFactory;
    this.initializeSummonerStep = initializeSummonerStep;
    this.initializeActiveGameStep = initializeActiveGameStep;
    this.syncGameStep = syncGameStep;
    this.syncSummonerGameStep = syncSummonerGameStep;
  }

  @Bean(JOB_NAME)
  public Job spectateGameJob() {

    return jobBuilderFactory
        .get(JOB_NAME)
        .incrementer(new RunIdIncrementer())
        .start(initializeSummonerStep)
        .next(initializeActiveGameStep)
        .next(syncGameStep)
        .next(syncSummonerGameStep)
        // .next(notifyMessageStep)
        .build();
  }
}
