package com.yaini.data.repository;

import com.yaini.data.entity.QGameEntity;
import com.yaini.data.entity.QSummonerEntity;
import com.yaini.data.entity.QSummonerGameRelationEntity;
import com.yaini.data.entity.SummonerGameRelationEntity;
import com.yaini.data.projection.QSummonerGameProjection;
import com.yaini.data.projection.SummonerGameProjection;
import java.util.Collection;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class SummonerGameRelationQueryDslRepositoryImpl extends QuerydslRepositorySupport
    implements SummonerGameRelationQueryDslRepository {

  private final QSummonerGameRelationEntity relation =
      QSummonerGameRelationEntity.summonerGameRelationEntity;
  private final QGameEntity game = QGameEntity.gameEntity;
  private final QSummonerEntity summoner = QSummonerEntity.summonerEntity;

  public SummonerGameRelationQueryDslRepositoryImpl() {
    super(SummonerGameRelationEntity.class);
  }

  @Override
  public Collection<SummonerGameProjection> findByNoticeEquals(final Boolean notice) {

    return from(relation)
        .select(new QSummonerGameProjection(game, summoner, relation))
        .innerJoin(game)
        .on(relation.gameId.eq(game.id))
        .innerJoin(summoner)
        .on(relation.summonerId.eq(summoner.id))
        .where(relation.notice.eq(notice))
        .fetch();
  }
}
