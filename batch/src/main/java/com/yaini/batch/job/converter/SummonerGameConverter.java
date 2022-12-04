package com.yaini.batch.job.converter;

import com.yaini.batch.job.model.Game;
import com.yaini.batch.job.model.Summoner;
import com.yaini.batch.job.model.SummonerGame;
import com.yaini.data.entity.SummonerGameRelationEntity;
import com.yaini.data.projection.SummonerGameProjection;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SummonerGameConverter {

  public static SummonerGameRelationEntity to(final Game data) {
    if (data == null || data.getSummoner() == null) {
      return null;
    }

    Summoner summoner = data.getSummoner();

    return SummonerGameRelationEntity.builder()
        .id(summoner.getId() + "_" + data.getId())
        .summonerId(summoner.getId())
        .gameId(data.getId())
        .firstSpell(summoner.getFirstSpell())
        .secondSpell(summoner.getSecondSpell())
        .championId(summoner.getChampionId())
        .notice(false)
        .build();
  }

  public static SummonerGame from(final SummonerGameProjection projection) {
    if (projection == null) {
      return null;
    }

    return SummonerGame.builder()
        .id(projection.getId())
        .gameId(projection.getGameId())
        .mapId(projection.getMapId())
        .gameMode(projection.getGameMode())
        .gameType(projection.getGameType())
        .gameQueueId(projection.getGameQueueId())
        .gameStartTime(projection.getGameStartTime())
        .gameLength(projection.getGameLength())
        .participants(projection.getParticipants())
        .summonerId(projection.getSummonerId())
        .name(projection.getName())
        .firstSpell(projection.getFirstSpell())
        .secondSpell(projection.getSecondSpell())
        .championId(projection.getChampionId())
        .build();
  }

  public static SummonerGameRelationEntity to(final SummonerGame data) {
    if (data == null) {
      return null;
    }

    return SummonerGameRelationEntity.builder()
        .id(data.getId())
        .gameId(data.getGameId())
        .summonerId(data.getSummonerId())
        .firstSpell(data.getFirstSpell())
        .secondSpell(data.getSecondSpell())
        .championId(data.getChampionId())
        .build();
  }
}
