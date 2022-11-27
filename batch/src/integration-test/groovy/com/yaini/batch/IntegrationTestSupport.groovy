package com.yaini.batch

import com.yaini.batch.job.config.BatchConfig
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles("local")
@EnableBatchProcessing
@ComponentScan(basePackages = ["com.yaini.batch"])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = BatchConfig)
@SpringBatchTest
class IntegrationTestSupport extends Specification {

}