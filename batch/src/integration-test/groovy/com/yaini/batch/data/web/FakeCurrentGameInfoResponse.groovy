package com.yaini.batch.data.web

import com.yaini.batch.client.web.vo.CurrentGameInfoResponse
import com.yaini.batch.client.web.vo.ParticipantsResponse
import com.yaini.data.enumerated.GameMode
import com.yaini.data.enumerated.GameType
import com.yaini.data.enumerated.Spell
import net.bytebuddy.utility.RandomString

class FakeCurrentGameInfoResponse {

    static CurrentGameInfoResponse create(final String summonerId = RandomString.make()){

        return CurrentGameInfoResponse.builder()
                .gameId(1L)
                .mapId(2L)
                .gameMode(GameMode.ARAM)
                .gameType(GameType.CUSTOM_GAME)
                .gameQueueConfigId(42)
                .participants(List.of(getParticipants(summonerId)))
                .gameStartTime(1)
                .gameLength(2)
                .build()
    }

    private static ParticipantsResponse getParticipants(final String summonerId = RandomString.make()){

        return ParticipantsResponse.builder()
            .spell1Id(Spell.BOOST.id)
            .spell2Id(Spell.BARRIER.id)
            .summonerName(RandomString.make())
            .summonerId(summonerId)
            .championId(266)
            .build()
    }
}
