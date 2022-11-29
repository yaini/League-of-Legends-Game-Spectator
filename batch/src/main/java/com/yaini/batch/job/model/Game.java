package com.yaini.batch.job.model;

import com.yaini.data.enumerated.GameMode;
import com.yaini.data.enumerated.GameType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game {
  private Long id;
  private Long mapId;
  private GameMode gameMode;
  private GameType gameType;
  private Long gameQueueId;
  private String participants;
  private Long gameStartTime;
  private Long gameLength;
  private Summoner summoner;
}
