package com.yaini.data;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.yaini.data.entity")
@EnableJpaRepositories(basePackages = "com.yaini.data")
public class DataConfig {}
