package com.yaini.batch.data.entity;

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
@Table(name = "summoner")
public class SummonerGameRelationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  @Column(nullable = false)
  private String summonerId;

  @Column(nullable = false)
  private Long gameId;

  @Column(nullable = false)
  private Long firstSpell;

  @Column(nullable = false)
  private Long secondSpell;

  @Column(nullable = false)
  private Long championId;
}
