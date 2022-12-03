package com.yaini.data.enumerated;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Spell {
  BARRIER(21L, "SummonerBarrier"),
  BOOST(1L, "SummonerBoost"),
  DOT(14L, "SummonerDot"),
  EXHAUST(3L, "SummonerExhaust"),
  FLASH(4L, "SummonerFlash"),
  HASTE(6L, "SummonerHaste"),
  HEAL(7L, "SummonerHeal"),
  MANA(13L, "SummonerMana"),
  PORO_RECALL(30L, "SummonerPoroRecall"),
  PORO_THROW(31L, "SummonerPoroThrow"),
  SMITE(11L, "SummonerSmite"),
  SNOW_URF_SNOWBALL_MARK(39L, "SummonerSnowURFSnowballMark"),
  SNOWBALL(32L, "SummonerSnowball"),
  TELEPORT(12L, "SummonerTeleport"),
  ULT_BOOK_PLACE_HOLDER(54L, "SummonerUltBookPlaceHolder"),
  ULT_BOOK_SMITE_PLACE_HOLDER(55L, "SummonerUltBookSmitePlaceHolder");

  private final Long id;
  private final String name;
  private static final Map<Long, Spell> map = new HashMap<>();

  static {
    for (Spell spell : Spell.values()) {
      map.put(spell.getId(), spell);
    }
  }

  public static Spell get(final Long id) {
    return map.get(id);
  }
}
