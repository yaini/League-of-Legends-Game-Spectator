package com.yaini.batch.client.web;

import com.yaini.batch.client.web.vo.CurrentGameInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "RiotWebClient", url = "${riot.uri.kr}")
public interface RiotWebClient {

  @GetMapping(value = "/lol/spectator/v4/active-games/by-summoner/{encryptedSummonerId}")
  CurrentGameInfoResponse getCurrentGameBySummoner(
      final @RequestHeader("X-Riot-Token") String apiKey,
      final @PathVariable String encryptedSummonerId);
}
