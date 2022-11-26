package com.yaini.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "summoner_game_relation")
public class SummonerGameRelationEntity extends AuditEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "summoner_game_relation_id")
  private Long id;

  @Column(nullable = false)
  private String summonerAccountId;

  @Column(nullable = false)
  private Long gameId;

  @Column(nullable = false)
  private Long firstSpell;

  @Column(nullable = false)
  private Long secondSpell;

  @Column(nullable = false)
  private Long championId;
}
