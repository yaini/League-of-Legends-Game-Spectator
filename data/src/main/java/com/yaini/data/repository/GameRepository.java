package com.yaini.data.repository;

import com.yaini.data.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

  boolean existsById(final Long id);
}
