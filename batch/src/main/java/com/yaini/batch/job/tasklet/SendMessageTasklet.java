package com.yaini.batch.job.tasklet;

import com.yaini.batch.client.web.DiscordWebClient;
import com.yaini.batch.client.web.vo.MessageRequest;
import com.yaini.batch.job.model.Game;
import com.yaini.batch.job.parameter.SpectatingJobParameter;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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
  private final String CONTENT = "🚀 %s 님이 게임을 시작했습니다! \n ";
  private final String EMBEDS = "참여자: %s";

  @Value("${discord.channel}") private String channel;

  @Override
  public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext)
      throws Exception {

    List<Game> games = parameter.getActiveGames();
    Embeds embeds =
        new Embeds(
            "test",
            "test",
            new Image(
                "http://ddragon.leagueoflegends.com/cdn/12.22.1/img/champion/Aatrox.png", 20, 20));

    for (Game game : games) {
      String content = String.format(CONTENT, game.getSummoner().getName());

      client.getCurrentGameBySummoner(
          channel, new MessageRequest(SENDER_NAME, content, List.of(embeds)));
    }

    return RepeatStatus.FINISHED;
  }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Image {
  private String url;
  private int height = 20;
  private int width = 20;
}
