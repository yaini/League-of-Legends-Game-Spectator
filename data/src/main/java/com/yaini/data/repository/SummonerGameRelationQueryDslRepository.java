package com.yaini.data.repository;

import com.yaini.data.projection.SummonerGameProjection;
import java.util.Collection;

public interface SummonerGameRelationQueryDslRepository {
  Collection<SummonerGameProjection> findByNoticeEquals(final Boolean notice);
}
