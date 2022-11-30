package com.yaini.api

import com.yaini.api.config.ApiConfig
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles("local")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = [ApiConfig, ApplicationTestConfig])
class IntegrationTestSupport extends Specification {

}

@Configuration
@ComponentScan(basePackages = ["com.yaini.api"])
class ApplicationTestConfig {

}
