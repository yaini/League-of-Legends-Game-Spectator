package com.yaini.data.enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Spell {
  BARRIER(21L),
  BOOST(1L),
  DOT(14L),
  EXHAUST(3L),
  FLASH(4L),
  HASTE(6L),
  HEAL(7L),
  MANA(13L),
  PORO_RECALL(30L),
  PORO_THROW(31L),
  SMITE(11L),
  SNOW_URF_SNOWBALL_MARK(39L),
  SNOWBALL(32L),
  TELEPORT(12L),
  ULT_BOOK_PLACE_HOLDER(54L),
  ULT_BOOK_SMITE_PLACE_HOLDER(55L);

  private final Long key;
}
