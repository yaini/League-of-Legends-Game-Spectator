package com.yaini.batch.job.step;

import com.yaini.batch.job.tasklet.SendMessageTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class SendMessageStep {

  public static final String STEP_NAME = "SEND_MESSAGE_STEP";

  private final StepBuilderFactory stepBuilderFactory;
  private final SendMessageTasklet sendMessageTasklet;

  @Bean(STEP_NAME)
  @JobScope
  public Step initializeSummonerStep() {
    return stepBuilderFactory.get(STEP_NAME).tasklet(sendMessageTasklet).build();
  }
}
