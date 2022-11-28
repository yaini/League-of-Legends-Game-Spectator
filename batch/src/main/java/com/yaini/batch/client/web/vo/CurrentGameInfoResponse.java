package com.yaini.batch.client.web.vo;

import com.yaini.data.enumerated.GameMode;
import com.yaini.data.enumerated.GameType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrentGameInfoResponse {
  private Long gameId;
  private Long mapId;
  private GameMode gameMode;
  private GameType gameType;
  private Long gameQueueConfigId;
  private List<ParticipantsResponse> participants;
  private Long gameStartTime;
  private Long gameLength;
}
