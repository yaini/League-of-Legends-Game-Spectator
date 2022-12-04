package com.yaini.batch.job.model;

import com.yaini.data.enumerated.GameMode;
import com.yaini.data.enumerated.GameType;
import com.yaini.data.enumerated.Spell;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SummonerGame {

  private String id;
  private Long gameId;
  private Long mapId;
  private GameMode gameMode;
  private GameType gameType;
  private Long gameQueueId;
  private Long gameStartTime;
  private Long gameLength;
  private String participants;

  private String summonerId;
  private String name;
  private Spell firstSpell;
  private Spell secondSpell;
  private Long championId;
}
