package com.yaini.batch.job.converter;

import com.yaini.batch.job.model.GameQueue;
import com.yaini.data.entity.GameQueueEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GameQueueConverter {

  public static GameQueueEntity to(final GameQueue data) {
    if (data == null) {
      return null;
    }

    return GameQueueEntity.builder()
        .id(data.getQueueId())
        .map(data.getMap())
        .description(data.getDescription())
        .notes(data.getNotes())
        .build();
  }
}
