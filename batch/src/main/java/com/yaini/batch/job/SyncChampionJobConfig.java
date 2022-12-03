package com.yaini.batch.job;

import com.yaini.batch.job.step.SyncChampionStep;
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
    havingValue = SyncChampionJobConfig.JOB_NAME)
public class SyncChampionJobConfig {

  public static final String JOB_NAME = "SYNC_CHAMPION_JOB";

  private final JobBuilderFactory jobBuilderFactory;
  private final Step syncChampionStep;

  public SyncChampionJobConfig(
      final JobBuilderFactory jobBuilderFactory,
      final @Qualifier(SyncChampionStep.STEP_NAME) Step syncChampionStep) {
    this.jobBuilderFactory = jobBuilderFactory;
    this.syncChampionStep = syncChampionStep;
  }

  @Bean(JOB_NAME)
  public Job syncGameQueueJob() {

    return jobBuilderFactory
        .get(JOB_NAME)
        .incrementer(new RunIdIncrementer())
        .start(syncChampionStep)
        .build();
  }
}
