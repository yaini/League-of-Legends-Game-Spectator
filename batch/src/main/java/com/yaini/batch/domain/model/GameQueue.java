package com.yaini.batch.domain.model;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class GameQueue {
  private Long queueId;
  private String map;
  private String description;
  private String notes;
}
