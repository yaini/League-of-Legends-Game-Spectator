package com.yaini.data.repository;

import com.yaini.data.entity.SummonerGameRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummonerGameRelationRepository
    extends JpaRepository<SummonerGameRelationEntity, Long> {

  Boolean existsBySummonerIdAndGameId(final String summonerId, final Long gameId);
}
