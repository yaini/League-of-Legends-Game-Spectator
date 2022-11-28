package com.yaini.batch.job.converter;

import com.yaini.batch.client.web.vo.CurrentGameInfoResponse;
import com.yaini.batch.client.web.vo.ParticipantsResponse;
import com.yaini.batch.job.model.Game;
import com.yaini.data.entity.GameEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GameConverter {

  public static Game from(final String accountId, final CurrentGameInfoResponse response) {
    if (response == null) {
      return null;
    }

    Map<String, ParticipantsResponse> participantsMap =
        response.getParticipants().stream()
            .collect(HashMap::new, (m, v) -> m.put(v.getSummonerId(), v), HashMap::putAll);

    List<String> participantsName =
        response.getParticipants().stream()
            .map(ParticipantsResponse::getSummonerName)
            .collect(Collectors.toUnmodifiableList());

    return Game.builder()
        .id(response.getGameId())
        .mapId(response.getMapId())
        .summonerId(accountId)
        .summonerName(participantsMap.get(accountId).getSummonerName())
        .gameMode(response.getGameMode())
        .gameType(response.getGameType())
        .gameQueueId(response.getGameQueueConfigId())
        .participants(String.join(", ", participantsName))
        .gameStartTime(response.getGameStartTime())
        .gameLength(response.getGameLength())
        .build();
  }

  public static GameEntity to(final Game item) {
    if (item == null) {
      return null;
    }

    return GameEntity.builder()
        .id(item.getId())
        .mapId(item.getMapId())
        .gameMode(item.getGameMode())
        .gameType(item.getGameType())
        .gameQueueId(item.getGameQueueId())
        .participants(item.getParticipants())
        .gameStartTime(item.getGameStartTime())
        .gameLength(item.getGameLength())
        .build();
  }
}
