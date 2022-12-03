package com.yaini.batch.job.usecase;

import com.yaini.batch.job.usecase.query.GetAllGameQueueMapQuery;
import com.yaini.data.repository.GameQueueRepository;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetAllGameQueueUseCase {

  private final GameQueueRepository repository;

  public Map<Long, String> execute(final GetAllGameQueueMapQuery query) {

    return repository.findAllBy().stream()
        .collect(HashMap::new, (m, v) -> m.put(v.getId(), v.getMap()), HashMap::putAll);
  }
}
