package com.yaini.api.domain.usecase;

import com.yaini.api.domain.command.SaveSummonerCommand;
import com.yaini.api.domain.converter.SummonerConverter;
import com.yaini.api.domain.model.Summoner;
import com.yaini.data.repository.SummonerRepository;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Validated
@Transactional
@RequiredArgsConstructor
@Service
public class SaveSummonerUseCase {

  private final SummonerRepository repository;

  public Summoner execute(final @Valid SaveSummonerCommand command) {

    Summoner summoner = command.getSummoner();

    return SummonerConverter.from(repository.save(SummonerConverter.to(summoner)));
  }
}
