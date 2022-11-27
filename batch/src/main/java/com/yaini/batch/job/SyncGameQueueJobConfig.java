package com.yaini.batch.job;

import com.yaini.batch.job.step.SyncGameQueueStep;
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
    havingValue = SyncGameQueueJobConfig.JOB_NAME)
public class SyncGameQueueJobConfig {

  public static final String JOB_NAME = "SYNC_GAME_QUEUE_JOB";

  private final JobBuilderFactory jobBuilderFactory;
  private final Step syncGameQueueStep;

  public SyncGameQueueJobConfig(
      final JobBuilderFactory jobBuilderFactory,
      final @Qualifier(SyncGameQueueStep.STEP_NAME) Step syncGameQueueStep) {
    this.jobBuilderFactory = jobBuilderFactory;
    this.syncGameQueueStep = syncGameQueueStep;
  }

  @Bean(JOB_NAME)
  public Job syncGameQueueJob() {

    return jobBuilderFactory
        .get(JOB_NAME)
        .incrementer(new RunIdIncrementer())
        .start(syncGameQueueStep)
        .build();
  }
}
