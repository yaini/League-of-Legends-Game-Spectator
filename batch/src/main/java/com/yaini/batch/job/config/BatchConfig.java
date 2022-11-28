package com.yaini.batch.job.config;

import com.yaini.data.DataConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.yaini.batch")
@Import({DataConfig.class})
public class BatchConfig {}
