package com.yaini.api.controller.request.converter;

import com.yaini.api.controller.request.SummonerRequest;
import com.yaini.api.domain.command.SaveSummonerCommand;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SummonerRequestConverter {

  public static SaveSummonerCommand to(final SummonerRequest request) {
    if (request == null) {
      return null;
    }

    return SaveSummonerCommand.builder()
        .id(request.getId())
        .accountId(request.getAccountId())
        .puuid(request.getPuuid())
        .name(request.getName())
        .profileIconId(request.getProfileIconId())
        .revisionDate(request.getRevisionDate())
        .summonerLevel(request.getSummonerLevel())
        .build();
  }
}
