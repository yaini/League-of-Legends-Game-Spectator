package com.yaini.api.controller

import com.google.gson.Gson
import com.yaini.api.IntegrationTestSupport
import com.yaini.api.controller.request.SummonerRequest
import com.yaini.api.data.summoner.SummonerDataInitializer
import com.yaini.data.entity.SummonerEntity
import com.yaini.data.enumerated.Region
import net.bytebuddy.utility.RandomString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Subject

import java.time.LocalDateTime

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class SummonerControllerSpec extends IntegrationTestSupport {

    @Subject
    @Autowired
    private SummonerController controller

    private MockMvc mvc
    private Gson gson

    def setup(){
        mvc = MockMvcBuilders.standaloneSetup(controller).build()
        gson = new Gson()
    }

    def "사용자는 원하는 소환사를 저장할 수 있다."(){
        given:
        SummonerRequest request = SummonerRequest.builder()
                .id(RandomString.make())
                .accountId(RandomString.make())
                .puuid(RandomString.make())
                .name(RandomString.make())
                .profileIconId(1L)
                .revisionDate(1L)
                .summonerLevel(1)
                .build()

        when:
        def result = mvc.perform(post(SummonerController.PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(gson.toJson(request)))

        then:
        result.andExpect(status().isCreated())
    }
}