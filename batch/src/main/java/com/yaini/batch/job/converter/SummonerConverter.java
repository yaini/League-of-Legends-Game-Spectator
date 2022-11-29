package com.yaini.batch.job.converter;

import com.yaini.batch.client.web.vo.ParticipantsResponse;
import com.yaini.batch.job.model.Summoner;
import com.yaini.data.enumerated.Spell;
import com.yaini.data.projection.SummonerProjection;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SummonerConverter {

  public static Summoner from(final ParticipantsResponse response) {
    if (response == null) {
      return null;
    }

    return Summoner.builder()
        .id(response.getSummonerId())
        .name(response.getSummonerName())
        .firstSpell(Spell.get(response.getSpell1Id()))
        .secondSpell(Spell.get(response.getSpell2Id()))
        .championId(response.getChampionId())
        .build();
  }

  public static Summoner from(final SummonerProjection projection) {
    if (projection == null) {
      return null;
    }

    return Summoner.builder().id(projection.getId()).name(projection.getName()).build();
  }
}
