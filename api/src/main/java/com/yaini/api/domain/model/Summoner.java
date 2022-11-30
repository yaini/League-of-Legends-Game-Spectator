package com.yaini.api.domain.model;

import com.yaini.data.enumerated.Region;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Summoner {
  private String id;
  private String accountId;
  private String puuid;
  private String name;
  private Long profileIconId;
  private Long revisionDate;
  private Integer summonerLevel;
  private Region region;
}
