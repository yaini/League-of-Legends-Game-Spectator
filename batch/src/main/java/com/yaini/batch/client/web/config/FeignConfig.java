package com.yaini.batch.client.web.config;

import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.yaini.batch.client.web")
public class FeignConfig {

  public static final long RETRY_PERIOD = 100;
  public static final long RETRY_MAX_PERIOD = 2000;
  public static final int RETRY_MAX_ATTEMPTS = 3;

  @Bean
  public Retryer retryer() {

    return new Retryer.Default(RETRY_PERIOD, RETRY_MAX_PERIOD, RETRY_MAX_ATTEMPTS);
  }

  @Bean
  public ErrorDecoder errorDecoder() {

    return new WebClientErrorDecoder();
  }
}
