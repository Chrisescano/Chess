package com.christian.games.parser.fen;

import com.christian.games.parser.Parser;
import com.christian.games.pojo.Fen;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FenParser implements Parser<String,Fen> {

  private static final Logger log = LoggerFactory.getLogger(FenParser.class);

  private final BoardStateParser boardStateParser = new BoardStateParser();
  private final ColorParser activeColorParser = new ColorParser(List.of("w"), List.of("b"));
  private final CastlingRightsParser castlingRightsParser = new CastlingRightsParser();
  private final EnPassantParser enPassantParser = new EnPassantParser();
  private final NumberParser halfMoveParser = new NumberParser(0, 100);
  private final NumberParser fullMoveParser = new NumberParser(0, 50);

  @Override
  public Fen parse(String input) {
    if (input == null) {
      return null;
    }

    log.info("Parsing input {}", input);
    String[] fenComponents = input.split(" ");
    return new Fen(
        boardStateParser.parse(getString(fenComponents, 0)),
        activeColorParser.parse(getString(fenComponents, 1)),
        castlingRightsParser.parse(getString(fenComponents, 2)),
        enPassantParser.parse(getString(fenComponents, 3)),
        halfMoveParser.parse(getString(fenComponents, 4)),
        fullMoveParser.parse(getString(fenComponents, 5))
    );
  }

  @Override
  public String unparse(Fen input) {
    return "";
  }

  private String getString(final String[] array, final int index) {
    if (array == null) {
      return null;
    }
    try {
      return array[index];
    } catch (Exception e) {
      return null;
    }
  }
}
