package com.christian.games.parser.fen;

import com.christian.games.parser.Parser;

public class NumberParser implements Parser<String, Integer> {

  private final int mininum;
  private final int maximum;

  public NumberParser(final int mininum, final int maximum) {
    this.mininum = mininum;
    this.maximum = maximum;
  }

  @Override
  public Integer parse(String input) {
    if (input == null) {
      return null;
    }

    try {
      int parsedInput = Integer.parseInt(input);
      if (parsedInput >= mininum && parsedInput <= maximum) {
        return parsedInput;
      }
    } catch (NumberFormatException ignore) {
    }

    return null;
  }

  @Override
  public String unparse(Integer input) {
    return "";
  }
}
