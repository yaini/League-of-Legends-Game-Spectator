package com.yaini.batch.job.usecase.query;

import com.yaini.batch.job.model.Summoner;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAllActiveGameQuery {
  private List<Summoner> summoners;
}
