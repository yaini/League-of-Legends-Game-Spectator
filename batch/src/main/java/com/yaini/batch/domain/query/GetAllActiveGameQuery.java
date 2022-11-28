package com.yaini.batch.domain.query;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAllActiveGameQuery {
  List<String> summonerIds;
}
