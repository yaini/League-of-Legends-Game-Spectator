package com.yaini.batch.client

import com.yaini.batch.IntegrationTestSupport
import com.yaini.batch.client.web.RiotWebClient
import com.yaini.batch.data.summoner.SummonerDataInitializer
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

import java.time.LocalDateTime

class RiotWebClientSpec extends IntegrationTestSupport {

    @Subject
    @Autowired
    private RiotWebClient webClient

    def "사용자는 라이엇 API를 통해 소환사 정보를 가져올 수 있다."() {
        given:
        String apiKey = "MOCK_API_KEY"
        String expect = "MOCK_SUMMONER_ID"

        when:
        def actual = webClient.getCurrentGameBySummoner(apiKey, expect)

        then:
        actual != null
        actual.participants.summonerId.contains(expect)
    }
}