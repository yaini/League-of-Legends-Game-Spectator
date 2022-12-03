package com.yaini.batch.job.tasklet;

import com.yaini.batch.client.web.DiscordWebClient;
import com.yaini.batch.client.web.vo.EmbedRequest;
import com.yaini.batch.client.web.vo.ImageRequest;
import com.yaini.batch.client.web.vo.MessageRequest;
import com.yaini.batch.job.model.Game;
import com.yaini.batch.job.model.Summoner;
import com.yaini.batch.job.parameter.SpectatingJobParameter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
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
  private final String TITLE = "🚀 %s 님이 게임을 시작했습니다! \n ";
  private final String CONTENT =
      "**참여자:** %s \n"
          + "**게임 모드:** %s \n"
          + "**게임 타입:** %s \n"
          + "**게임 큐 타입:** %s \n"
          + "**게임 시작 시간:** %s \n"
          + "**스펠:** %s %s \n"
          + "**챔피언:** %s";

  @Value("${discord.channel}") private String channel;

  @Value("${riot.uri.champion}") private String championImagePath;

  @Value("${riot.uri.icon}") private String iconImagePath;

  @Override
  public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext)
      throws Exception {

    List<Game> games = parameter.getActiveGames();
    Map<Long, String> maps = parameter.getGameQueues();
    Map<Long, String> champions = parameter.getChampions();

    for (Game game : games) {

      Summoner summoner = game.getSummoner();
      String champion = champions.get(summoner.getChampionId());
      String map = maps.get(game.getGameQueueId());

      MessageRequest message =
          MessageRequest.builder()
              .username(SENDER_NAME)
              .avatarUrl(iconImagePath)
              .embeds(List.of(this.getEmbed(game, map, champion)))
              .build();

      client.getCurrentGameBySummoner(channel, message);
    }

    return RepeatStatus.FINISHED;
  }

  private EmbedRequest getEmbed(final Game game, final String map, final String champion) {
    if (game == null || game.getSummoner() == null) {
      return null;
    }

    Summoner summoner = game.getSummoner();
    LocalDateTime startTime =
        LocalDateTime.ofInstant(
            Instant.ofEpochMilli(game.getGameStartTime()), ZoneId.systemDefault());

    String description =
        String.format(
            CONTENT,
            game.getParticipants(),
            game.getGameMode(),
            game.getGameType(),
            map,
            startTime,
            summoner.getFirstSpell(),
            summoner.getSecondSpell(),
            champion);

    return EmbedRequest.builder()
        .title(String.format(TITLE, summoner.getName()))
        .description(description)
        .thumbnail(new ImageRequest(championImagePath + champion + ".png"))
        .build();
  }
}
