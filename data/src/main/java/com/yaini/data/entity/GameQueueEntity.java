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
@Table(name = "game_queue")
public class GameQueueEntity extends AuditEntity {
  @Id
  @Column(name = "game_queue_id", nullable = false)
  private Long id;

  @Column(nullable = false)
  private String map;

  @Column private String description;

  @Column private String notes;
}
