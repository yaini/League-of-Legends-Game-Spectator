package com.yaini.batch.job.usecase;

import com.yaini.batch.job.usecase.query.GetAllChampionNameQuery;
import com.yaini.data.repository.ChampionRepository;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetAllChampionUseCase {

  private final ChampionRepository repository;

  public Map<Long, String> execute(final GetAllChampionNameQuery query) {

    return repository.findAllBy().stream()
        .collect(HashMap::new, (m, v) -> m.put(v.getId(), v.getName()), HashMap::putAll);
  }
}
