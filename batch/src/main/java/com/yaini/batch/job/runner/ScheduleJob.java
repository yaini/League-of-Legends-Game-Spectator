package com.yaini.batch.job.runner;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ScheduleJob extends QuartzJobBean {

  private final Job job;
  private final JobLauncher jobLauncher;

  @SneakyThrows
  @Override
  protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {

    JobParameters jobParameters =
        new JobParametersBuilder()
            .addString("DATETIME", LocalDateTime.now().toString())
            .toJobParameters();

    jobLauncher.run(job, jobParameters);
  }
}
