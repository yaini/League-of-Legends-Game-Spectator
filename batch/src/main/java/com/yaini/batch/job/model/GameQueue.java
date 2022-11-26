package com.yaini.batch.job.model;

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
