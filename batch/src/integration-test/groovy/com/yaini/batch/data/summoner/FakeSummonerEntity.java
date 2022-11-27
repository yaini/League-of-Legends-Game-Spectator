package com.yaini.batch.data.summoner;

import com.yaini.data.entity.SummonerEntity;
import net.bytebuddy.utility.RandomString;

public class FakeSummonerEntity {

  static SummonerEntity create() {

    return SummonerEntity.builder()
        .id(RandomString.make())
        .accountId(RandomString.make())
        .puuid(RandomString.make())
        .name(RandomString.make())
        .profileIconId(1L)
        .revisionDate(1L)
        .summonerLevel(1)
        .build();
  }
}
