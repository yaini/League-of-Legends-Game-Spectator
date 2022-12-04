package com.yaini.data.projection;

import com.querydsl.core.annotations.QueryProjection;
import com.yaini.data.entity.GameEntity;
import com.yaini.data.entity.SummonerEntity;
import com.yaini.data.entity.SummonerGameRelationEntity;
import com.yaini.data.enumerated.GameMode;
import com.yaini.data.enumerated.GameType;
import com.yaini.data.enumerated.Spell;
import java.io.Serializable;
import lombok.Getter;

@Getter
public class SummonerGameProjection implements Serializable {

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

  @QueryProjection
  public SummonerGameProjection(
      final GameEntity gameEntity,
      final SummonerEntity summonerEntity,
      final SummonerGameRelationEntity relationEntity) {
    if (gameEntity == null || summonerEntity == null || relationEntity == null) {
      return;
    }

    this.gameId = gameEntity.getId();
    this.mapId = gameEntity.getMapId();
    this.gameMode = gameEntity.getGameMode();
    this.gameType = gameEntity.getGameType();
    this.gameQueueId = gameEntity.getGameQueueId();
    this.gameStartTime = gameEntity.getGameStartTime();
    this.gameLength = gameEntity.getGameLength();
    this.participants = gameEntity.getParticipants();

    this.summonerId = summonerEntity.getId();
    this.name = summonerEntity.getName();

    this.id = relationEntity.getId();
    this.firstSpell = relationEntity.getFirstSpell();
    this.secondSpell = relationEntity.getSecondSpell();
    this.championId = relationEntity.getChampionId();
  }
}
