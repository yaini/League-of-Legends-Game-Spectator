package com.yaini.api.data.summoner;

import com.yaini.data.entity.SummonerEntity;
import com.yaini.data.enumerated.Region;
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
        .region(Region.KR)
        .build();
  }
}
