package com.yaini.data.repository;

import com.yaini.data.entity.SummonerEntity;
import com.yaini.data.projection.SummonerProjection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummonerRepository extends JpaRepository<SummonerEntity, Long> {

  List<SummonerProjection> findAllBy();
}
