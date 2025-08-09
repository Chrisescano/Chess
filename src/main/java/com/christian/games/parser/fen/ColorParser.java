package com.christian.games.parser.fen;

import static com.christian.games.piece.Color.BLACK;
import static com.christian.games.piece.Color.WHITE;

import com.christian.games.parser.Parser;
import com.christian.games.piece.Color;
import java.util.List;

public class ColorParser implements Parser<String, Color> {

  private final List<String> whiteColorOptions;
  private final List<String> blackColorOptions;

  public ColorParser(final List<String> whiteColorOptions, List<String> blackColorOptions) {
    this.whiteColorOptions = whiteColorOptions;
    this.blackColorOptions = blackColorOptions;
  }

  @Override
  public Color parse(String input) {
    if (input == null) {
      return null;
    }

    if (whiteColorOptions.contains(input)) {
      return WHITE;
    }

    if (blackColorOptions.contains(input)) {
      return BLACK;
    }

    return null;
  }

  @Override
  public String unparse(Color input) {
    return "";
  }
}
