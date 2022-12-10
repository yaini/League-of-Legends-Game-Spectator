package com.yaini.batch.job

import com.yaini.batch.BatchIntegrationTestSupport
import com.yaini.batch.IntegrationTestSupport
import com.yaini.batch.data.summoner.SummonerDataInitializer
import com.yaini.data.entity.SummonerEntity
import com.yaini.data.repository.SummonerRepository
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.TestPropertySource

import java.time.LocalDateTime

@Rollback
@TestPropertySource(properties = ["spring.batch.job.enabled=false", "spring.batch.job.names=SPECTATE_GAME_JOB"])
class SpectateGameJobSpec extends BatchIntegrationTestSupport {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils

    @Autowired
    private SummonerDataInitializer initializer

    def setup(){

        initializer.execute()
    }

    def "사용자는 유저별 게임 정보를 조회할 수 있다."() {
        given:
        JobParameters jobParameters =
                new JobParametersBuilder()
                        .addString("date", LocalDateTime.now().toString())
                        .toJobParameters()

        when:
        final JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters)

        then:
        jobExecution.status == BatchStatus.COMPLETED
    }
}