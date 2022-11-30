package com.yaini.api.domain.converter;

import com.yaini.api.domain.model.Summoner;
import com.yaini.data.entity.SummonerEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SummonerConverter {

  public static SummonerEntity to(final Summoner data) {
    if (data == null) {
      return null;
    }

    return SummonerEntity.builder()
        .id(data.getId())
        .accountId(data.getAccountId())
        .puuid(data.getPuuid())
        .name(data.getName())
        .profileIconId(data.getProfileIconId())
        .revisionDate(data.getRevisionDate())
        .summonerLevel(data.getSummonerLevel())
        .region(data.getRegion())
        .build();
  }

  public static Summoner from(final SummonerEntity entity) {
    if (entity == null) {
      return null;
    }

    return Summoner.builder()
        .id(entity.getId())
        .accountId(entity.getAccountId())
        .puuid(entity.getPuuid())
        .name(entity.getName())
        .profileIconId(entity.getProfileIconId())
        .revisionDate(entity.getRevisionDate())
        .summonerLevel(entity.getSummonerLevel())
        .region(entity.getRegion())
        .build();
  }
}
