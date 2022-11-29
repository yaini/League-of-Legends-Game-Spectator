package com.yaini.batch.job.usecase;

import com.yaini.batch.job.model.Summoner;
import com.yaini.batch.job.usecase.query.GetAllSummonerNameQuery;
import com.yaini.data.repository.SummonerRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetAllSummonerUseCase {

  private final SummonerRepository repository;

  public List<Summoner> execute(final GetAllSummonerNameQuery query) {

    return repository.findAllBy().stream()
        .map(v -> new Summoner(v.getId(), v.getName()))
        .collect(Collectors.toUnmodifiableList());
  }
}
