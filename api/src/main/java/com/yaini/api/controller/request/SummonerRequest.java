package com.yaini.api.controller.request;

import lombok.Getter;

@Getter
public class SummonerRequest {
  private String id;
  private String accountId;
  private String puuid;
  private String name;
  private Long profileIconId;
  private Long revisionDate;
  private Integer summonerLevel;
}
