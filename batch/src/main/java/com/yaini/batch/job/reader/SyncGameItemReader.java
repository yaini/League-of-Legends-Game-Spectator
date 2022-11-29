package com.yaini.batch.job.reader;

import com.yaini.batch.job.model.Game;
import com.yaini.batch.job.parameter.SpectatingJobParameter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SyncGameItemReader implements ItemReader<Game> {

  private final SpectatingJobParameter jobParameter;
  private List<Game> items;
  private int index;

  @Override
  public Game read() {
    if (items == null) {
      items = jobParameter.getActiveGames();
      index = 0;
    }

    if (index >= items.size()) {
      return null;
    }

    return items.get(index);
  }
}
