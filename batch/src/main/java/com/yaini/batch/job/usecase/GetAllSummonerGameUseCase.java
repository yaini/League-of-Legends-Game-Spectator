package com.yaini.batch.job.usecase;

import com.yaini.batch.job.converter.SummonerGameConverter;
import com.yaini.batch.job.model.SummonerGame;
import com.yaini.batch.job.usecase.query.GetAllSummonerGameQuery;
import com.yaini.data.repository.SummonerGameRelationRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetAllSummonerGameUseCase {

  private final SummonerGameRelationRepository repository;

  public List<SummonerGame> execute(final GetAllSummonerGameQuery query) {

    return repository.findByNoticeEquals(query.getNotice()).stream()
        .map(SummonerGameConverter::from)
        .collect(Collectors.toUnmodifiableList());
  }
}
