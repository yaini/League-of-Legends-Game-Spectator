package com.yaini.data.entity;

import com.yaini.data.enumerated.Spell;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@With
@Table(name = "summoner_game_relation")
public class SummonerGameRelationEntity extends AuditEntity {
  @Id
  @Column(name = "summoner_game_relation_id")
  private String id;

  @Column(nullable = false)
  private String summonerId;

  @Column(nullable = false)
  private Long gameId;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private Spell firstSpell;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private Spell secondSpell;

  @Column(nullable = false)
  private Long championId;

  @Column(nullable = false)
  private Boolean notice;
}
