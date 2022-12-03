package com.yaini.batch.job.converter;

import com.yaini.batch.job.model.Champion;
import com.yaini.data.entity.ChampionEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ChampionConverter {

  public static ChampionEntity to(final Champion data) {
    if (data == null) {
      return null;
    }

    return ChampionEntity.builder().id(Long.parseLong(data.getKey())).name(data.getName()).build();
  }
}
