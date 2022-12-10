package com.yaini.batch.job.runner;

import static org.quartz.JobBuilder.newJob;

import com.yaini.batch.job.SpectateGameJobConfig;
import lombok.RequiredArgsConstructor;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("local")
@RequiredArgsConstructor
@Component
public class JobScheduler implements ApplicationRunner {

  private final Scheduler scheduler;

  @Override
  public void run(final ApplicationArguments args) throws Exception {

    JobDetail jobDetail =
        buildJobDetail(ScheduleJob.class, SpectateGameJobConfig.JOB_NAME, "batch");

    try {
      scheduler.scheduleJob(jobDetail, this.getTrigger("0/10 * * * * ?"));
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

  public Trigger getTrigger(final String scheduleExp) {

    return TriggerBuilder.newTrigger()
        .withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp))
        .build();
  }

  public JobDetail buildJobDetail(
      final Class<? extends Job> job, final String name, final String group) {

    JobDataMap jobDataMap = new JobDataMap();

    return newJob(job).withIdentity(name, group).usingJobData(jobDataMap).build();
  }
}
