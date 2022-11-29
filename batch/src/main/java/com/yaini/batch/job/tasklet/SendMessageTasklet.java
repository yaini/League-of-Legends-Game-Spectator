package com.yaini.batch.job.tasklet;

import com.yaini.batch.client.web.DiscordWebClient;
import com.yaini.batch.client.web.vo.MessageRequest;
import com.yaini.batch.job.model.Game;
import com.yaini.batch.job.parameter.SpectatingJobParameter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SendMessageTasklet implements Tasklet {

  private final DiscordWebClient client;
  private final SpectatingJobParameter parameter;
  private final String SENDER_NAME = "LOL_GAME_NOTICE";
  private final String FORMAT = "🚀 %s 님이 게임을 시작했습니다! \n 참여자: %s";

  @Value("${discord.channel}") private String channel;

  @Override
  public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext)
      throws Exception {

    List<Game> games = parameter.getActiveGames();

    for (Game game : games) {
      String content = String.format(FORMAT, game.getSummoner().getName(), game.getParticipants());

      client.getCurrentGameBySummoner(channel, new MessageRequest(SENDER_NAME, content));
    }

    return RepeatStatus.FINISHED;
  }
}
