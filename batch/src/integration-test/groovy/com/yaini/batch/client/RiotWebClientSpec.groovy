package com.yaini.batch.client

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.google.gson.Gson
import com.yaini.batch.IntegrationTestSupport
import com.yaini.batch.client.web.RiotWebClient
import com.yaini.batch.client.web.support.exception.ResourceNotFoundException
import com.yaini.batch.data.web.FakeCurrentGameInfoResponse
import feign.FeignException
import org.apache.http.protocol.HTTP
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import spock.lang.Subject


import static com.yaini.batch.client.web.config.FeignConfig.RETRY_MAX_ATTEMPTS
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import static com.github.tomakehurst.wiremock.client.WireMock.verify
import static com.github.tomakehurst.wiremock.client.WireMock.get

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
        mockServer.resetAll()
    }

    def "클라이언트는 라이엇 API를 통해 소환사 정보를 가져올 수 있다."() {
        given:
        def apiKey = "MOCK_API_KEY"
        def path = urlEqualTo(webClient.ACTIVE_GAME_PATH+MOCK_SUMMONER_ID)
        def response = WireMock.aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader(HTTP.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .withBody(gson.toJson(FakeCurrentGameInfoResponse.create(MOCK_SUMMONER_ID)))

        stubFor(get(path).willReturn(response))

        when:
        def actual = webClient.getCurrentGameBySummoner(apiKey, MOCK_SUMMONER_ID)

        then:
        actual != null
        actual.participants.summonerId.contains(MOCK_SUMMONER_ID)

        and:
        verify(getRequestedFor(path))
    }

    def "클라이언트는 라이엇 API에서 플레이중인 게임이 없다면 NOT_FOUND 예외를 발생시킨다."() {
        given:
        def apiKey = "MOCK_API_KEY"
        def path = urlEqualTo(webClient.ACTIVE_GAME_PATH+MOCK_SUMMONER_ID)
        def response = WireMock.aResponse()
                .withStatus(HttpStatus.NOT_FOUND.value())
                .withHeader(HTTP.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)

        stubFor(get(path).willReturn(response))

        when:
        webClient.getCurrentGameBySummoner(apiKey, MOCK_SUMMONER_ID)

        then:
        thrown(ResourceNotFoundException.class)

        and:
        verify(getRequestedFor(path))
    }

    def "클라이언트는 라이엇 API에서 서버 에러가 발생하면 설정된 재시도 회수만큼 다시 호출한다."() {
        given:
        def apiKey = "MOCK_API_KEY"
        def path = urlEqualTo(webClient.ACTIVE_GAME_PATH+MOCK_SUMMONER_ID)
        def response = WireMock.aResponse()
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .withHeader(HTTP.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)

        stubFor(get(path).willReturn(response))

        when:
        webClient.getCurrentGameBySummoner(apiKey, MOCK_SUMMONER_ID)

        then:
        thrown(FeignException.class)

        and:
        verify(RETRY_MAX_ATTEMPTS, getRequestedFor(path))
    }

    def "클라이언트는 라이엇 API의 응답 시간이 초과되면 재시도 후 예외를 발생한다."() {
        given:
        def apiKey = "MOCK_API_KEY"
        def path = urlEqualTo(webClient.ACTIVE_GAME_PATH+MOCK_SUMMONER_ID)
        def response = WireMock.aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader(HTTP.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .withBody(gson.toJson(FakeCurrentGameInfoResponse.create(MOCK_SUMMONER_ID)))
                .withFixedDelay(6000)

        stubFor(get(path).willReturn(response))

        when:
        webClient.getCurrentGameBySummoner(apiKey, MOCK_SUMMONER_ID)

        then:
        thrown(FeignException.class)

        and:
        verify(RETRY_MAX_ATTEMPTS, getRequestedFor(path))
    }

}