package com.yaini.batch.client.web;

import com.yaini.batch.client.web.vo.MessageRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "DiscordWebClient", url = "${discord.uri}")
public interface DiscordWebClient {

  @PostMapping("/{channel}")
  String getCurrentGameBySummoner(
      final @PathVariable String channel, final @RequestBody MessageRequest request);
}
