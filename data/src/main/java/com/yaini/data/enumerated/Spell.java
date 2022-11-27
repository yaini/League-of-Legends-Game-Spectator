package com.yaini.data.enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Spell {
  BARRIER(21),
  BOOST(1),
  DOT(14),
  EXHAUST(3),
  FLASH(4),
  HASTE(6),
  HEAL(7),
  MANA(13),
  PORO_RECALL(30),
  PORO_THROW(31),
  SMITE(11),
  SNOW_URF_SNOWBALL_MARK(39),
  SNOWBALL(32),
  TELEPORT(12),
  ULT_BOOK_PLACE_HOLDER(54),
  ULT_BOOK_SMITE_PLACE_HOLDER(55);

  private final Integer key;
}
