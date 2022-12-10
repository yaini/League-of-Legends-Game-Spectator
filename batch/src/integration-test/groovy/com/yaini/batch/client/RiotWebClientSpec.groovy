package com.yaini.batch.client

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.google.gson.Gson
import com.yaini.batch.IntegrationTestSupport
import com.yaini.batch.client.web.RiotWebClient
import com.yaini.batch.data.web.FakeCurrentGameInfoResponse
import org.apache.http.protocol.HTTP
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import spock.lang.Subject

import java.time.LocalDateTime

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor

@AutoConfigureWireMock(port = 80)
class RiotWebClientSpec extends IntegrationTestSupport {

    @Subject
    @Autowired
    private RiotWebClient webClient

    @Autowired
    private WireMockServer mockServer

    private String MOCK_SUMMONER_ID = "MOCK_SUMMONER_ID"
    private Gson gson

    def setup(){

        gson = new Gson()
        mockServer.start()
    }

    def cleanup(){
        mockServer.stop()
    }

    def "사용자는 라이엇 API를 통해 소환사 정보를 가져올 수 있다."() {
        given:
        def apiKey = "MOCK_API_KEY"
        def response = WireMock.aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader(HTTP.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .withBody(gson.toJson(FakeCurrentGameInfoResponse.create(MOCK_SUMMONER_ID)))

        stubFor(WireMock.get(WireMock.urlEqualTo(webClient.ACTIVE_GAME_PATH+MOCK_SUMMONER_ID)).willReturn(response))

        when:
        def actual = webClient.getCurrentGameBySummoner(apiKey, MOCK_SUMMONER_ID)

        then:
        actual != null
        actual.participants.summonerId.contains(MOCK_SUMMONER_ID)
    }
}