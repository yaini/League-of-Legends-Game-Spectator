package com.yaini.data.entity;

import com.yaini.data.enumerated.GameMode;
import com.yaini.data.enumerated.GameType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "game")
public class GameEntity {
  @Id
  @Column(name = "game_id", nullable = false)
  private Long id;

  @Column(nullable = false)
  private Long mapId;

  @Column(nullable = false)
  private GameMode gameMode;

  @Column(nullable = false)
  private GameType gameType;

  @Column(nullable = false)
  private Long gameQueueId;

  @Column(nullable = false)
  private Long gameStartTime;

  @Column(nullable = false)
  private Long gameLength;

  @Column(nullable = false)
  private String participants;
}
