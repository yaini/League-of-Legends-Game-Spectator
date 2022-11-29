package com.yaini.batch.job.model;

import com.yaini.data.enumerated.Spell;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Summoner {
  private String id;
  private String name;
  private Spell firstSpell;
  private Spell secondSpell;
  private Long championId;
}
