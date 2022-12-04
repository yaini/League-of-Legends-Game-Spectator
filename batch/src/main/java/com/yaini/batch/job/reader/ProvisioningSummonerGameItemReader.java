package com.yaini.batch.job.reader;

import com.yaini.batch.job.model.SummonerGame;
import com.yaini.batch.job.parameter.SpectatingJobParameter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProvisioningSummonerGameItemReader implements ItemReader<SummonerGame> {

  private final SpectatingJobParameter jobParameter;
  private List<SummonerGame> items;
  private int index;

  @Override
  public SummonerGame read() {
    if (items == null) {
      items = jobParameter.getSummonerGames();
      index = 0;
    }

    if (index >= items.size()) {
      return null;
    }

    return items.get(index++);
  }
}
