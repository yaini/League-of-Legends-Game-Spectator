package com.yaini.data.entity;

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
@Table(name = "queue")
public class GameQueueEntity {
  @Id
  @Column(name = "game_queue_id", nullable = false)
  private Long id;

  @Column(nullable = false)
  private String map;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private String notes;
}
