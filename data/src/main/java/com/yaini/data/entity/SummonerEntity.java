package com.yaini.data.entity;

import com.yaini.data.enumerated.Region;
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

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "summoner")
public class SummonerEntity extends AuditEntity {
  @Id
  @Column(name = "summoner_id", nullable = false)
  private String id;

  @Column(nullable = false)
  private String accountId;

  @Column(nullable = false)
  private String puuid;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Long profileIconId;

  @Column(nullable = false)
  private Long revisionDate;

  @Column(nullable = false)
  private Integer summonerLevel;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private Region region;
}
