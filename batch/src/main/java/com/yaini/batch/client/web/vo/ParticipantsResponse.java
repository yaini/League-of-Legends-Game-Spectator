package com.yaini.batch.client.web.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantsResponse {
  private Long spell1Id;
  private Long spell2Id;
  private String summonerName;
  private String summonerId;
  private Long championId;
}
