package com.yaini.api.config;

import com.yaini.data.DataConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.yaini.api")
@Import({DataConfig.class})
public class ApiConfig {}
