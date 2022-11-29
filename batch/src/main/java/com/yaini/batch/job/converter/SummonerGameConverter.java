package com.yaini.batch.job.converter;

import com.yaini.batch.job.model.Game;
import com.yaini.data.entity.SummonerGameRelationEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SummonerGameConverter {

  public static SummonerGameRelationEntity to(final Game item) {
    if (item == null || item.getSummoner() == null) {
      return null;
    }

    return SummonerGameRelationEntity.builder()
        .summonerId(item.getSummoner().getId())
        .gameId(item.getId())
        .build();
  }
}
