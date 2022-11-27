package com.yaini.batch.job.parameter;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.stereotype.Component;

@Getter
@JobScope
@Component
public class SpectatingJobParameter {
  @Setter private List<String> summonerAccountIds;
}
