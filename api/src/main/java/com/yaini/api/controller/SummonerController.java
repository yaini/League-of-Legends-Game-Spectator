package com.yaini.api.controller;

import static com.yaini.api.controller.SummonerController.PATH;

import com.yaini.api.controller.request.SummonerRequest;
import com.yaini.api.controller.request.converter.SummonerRequestConverter;
import com.yaini.api.domain.usecase.SaveSummonerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PATH)
@RequiredArgsConstructor
public class SummonerController {

  public static final String PATH = "/api/v1/summoners";

  private final SaveSummonerUseCase useCase;

  @PostMapping
  public ResponseEntity<Void> add(final @RequestBody SummonerRequest request) {
    useCase.execute(SummonerRequestConverter.to(request));

    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
