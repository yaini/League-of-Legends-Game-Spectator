package com.yaini.data.repository;

import com.yaini.data.entity.ChampionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionRepository extends JpaRepository<ChampionEntity, Long> {}
