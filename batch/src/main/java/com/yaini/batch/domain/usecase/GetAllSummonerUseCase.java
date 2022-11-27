package com.yaini.batch.domain.usecase;

import com.yaini.batch.domain.query.GetAllSummonerNameQuery;
import com.yaini.data.projection.NameProjection;
import com.yaini.data.repository.SummonerRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetAllSummonerUseCase {

  private final SummonerRepository repository;

  public List<String> execute(final GetAllSummonerNameQuery query) {

    return repository.getAll().stream()
        .map(NameProjection::getName)
        .collect(Collectors.toUnmodifiableList());
  }
}
