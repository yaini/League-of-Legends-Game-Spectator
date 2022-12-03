package com.yaini.data.repository;

import com.yaini.data.entity.GameQueueEntity;
import com.yaini.data.projection.GameQueueProjection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameQueueRepository extends JpaRepository<GameQueueEntity, Long> {

  List<GameQueueProjection> findAllBy();
}
