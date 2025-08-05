package com.christian.games.parser.fen;

import static com.christian.games.piece.Color.BLACK;
import static com.christian.games.piece.Color.WHITE;

import com.christian.games.parser.Parser;
import com.christian.games.piece.Color;

public class ActiveColorParser implements Parser<String, Color> {

  @Override
  public Color parse(String input) {
    return switch (input) {
      case "w" -> WHITE;
      case "b" -> BLACK;
      default -> null;
    };
  }

  @Override
  public String unparse(Color input) {
    return "";
  }
}
