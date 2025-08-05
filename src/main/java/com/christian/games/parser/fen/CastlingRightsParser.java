package com.christian.games.parser.fen;

import com.christian.games.parser.Parser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CastlingRightsParser implements Parser<String, boolean[]> {

  private final Pattern pattern = Pattern.compile("(Q)?(K)?(q)?(k)?");

  @Override
  public boolean[] parse(String input) {
    if (input == null) {
      return null;
    }

    if (input.equals("-")) {
      return new boolean[4];
    }

    Matcher matcher = pattern.matcher(input);
    if (!matcher.matches()) {
      return null;
    }

    return new boolean[] {
        matcher.group(1) != null,
        matcher.group(2) != null,
        matcher.group(3) != null,
        matcher.group(4) != null
    };
  }

  @Override
  public String unparse(boolean[] input) {
    return "";
  }
}
