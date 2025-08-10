package com.christian.games.parser.fen;

import static com.christian.games.piece.Color.BLACK;
import static com.christian.games.piece.Color.WHITE;

import com.christian.games.parser.Parser;
import com.christian.games.piece.Color;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColorParser implements Parser<String, Color> {

  private static final Logger log = LoggerFactory.getLogger(ColorParser.class);

  private final List<String> whiteColorOptions;
  private final List<String> blackColorOptions;
  private final boolean strict;

  public ColorParser(final List<String> whiteColorOptions, List<String> blackColorOptions) {
    this(whiteColorOptions, blackColorOptions, true);
  }

  public ColorParser(final List<String> whiteColorOptions, final List<String> blackColorOptions, final boolean strict) {
    this.whiteColorOptions = whiteColorOptions;
    this.blackColorOptions = blackColorOptions;
    this.strict = strict;
  }

  @Override
  public Color parse(String input) {
    if (input == null) {
      return null;
    }

    log.info("Parsing input {}", input);
    Color color;
    if (strict) {
      color = parseColorStrictly(input);
    } else {
      color = parseColorLeniently(input);
    }
    return color;
  }

  @Override
  public String unparse(Color input) {
    return "";
  }

  /*-- Helper Methods --*/

  private Color parseColorStrictly(final String input) {
    if (whiteColorOptions.contains(input)) {
      return WHITE;
    } else if (blackColorOptions.contains(input)) {
      return BLACK;
    } else {
      return null;
    }
  }

  private Color parseColorLeniently(final String input) {
    if (whiteColorOptions.stream().anyMatch(input::equalsIgnoreCase)) {
      return WHITE;
    } else if (blackColorOptions.stream().anyMatch(input::equalsIgnoreCase)) {
      return BLACK;
    } else {
      return null;
    }
  }
}
