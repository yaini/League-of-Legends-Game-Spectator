package com.yaini.batch.job.converter;

import com.yaini.batch.job.model.Game;
import com.yaini.batch.job.model.Summoner;
import com.yaini.data.entity.SummonerGameRelationEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SummonerGameConverter {

  public static SummonerGameRelationEntity to(final Game item) {
    if (item == null || item.getSummoner() == null) {
      return null;
    }

    Summoner summoner = item.getSummoner();

    return SummonerGameRelationEntity.builder()
        .summonerId(summoner.getId())
        .gameId(item.getId())
        .firstSpell(summoner.getFirstSpell())
        .secondSpell(summoner.getSecondSpell())
        .championId(summoner.getChampionId())
        .build();
  }
}
