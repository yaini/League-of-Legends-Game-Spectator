package com.yaini.api.domain.command;

import com.yaini.api.domain.model.Summoner;
import com.yaini.data.enumerated.Region;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public class SaveSummonerCommand {
  @NotEmpty private String id;
  @NotEmpty private String accountId;
  @NotEmpty private String puuid;
  @NotEmpty private String name;
  @NotNull private Long profileIconId;
  @NotNull private Long revisionDate;
  @NotNull private Integer summonerLevel;
  @Builder.Default private Region region = Region.KR;

  public Summoner getSummoner() {

    return Summoner.builder()
        .id(this.id)
        .accountId(this.accountId)
        .puuid(this.puuid)
        .name(this.name)
        .profileIconId(this.profileIconId)
        .revisionDate(this.revisionDate)
        .summonerLevel(this.summonerLevel)
        .region(this.region)
        .build();
  }
}
