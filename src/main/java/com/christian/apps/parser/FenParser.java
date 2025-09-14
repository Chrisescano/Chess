package com.christian.apps.parser;

import com.christian.apps.objects.Fen;
import com.christian.apps.piece.Piece;
import com.christian.apps.util.Position;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FenParser {

  private static final Logger log = LoggerFactory.getLogger(FenParser.class);
  private static final String EMPTY_STRING = "";

  /*-- Methods --*/

  public Fen parse(final String input) {
    if (input == null) {
      log.error("Cannot parse fen because input is null");
      return null;
    }

    final String[] fenComp = safelySplitInput(input);
    log.debug("Attempting to parse: [{}]", prettyFenComp(fenComp));

    return new Fen(parseBoardState(fenComp[0]));
  }

  /*-- Helper Methods --*/

  private String[] safelySplitInput(final String input) {
    final String[] origTokens = input.split(" ");
    final String[] result = new String[6];
    int min = Math.min(origTokens.length, result.length);
    System.arraycopy(origTokens, 0, result, 0, min);
    while (min < result.length) {
      result[min] = EMPTY_STRING;
      min++;
    }
    return result;
  }

  private String prettyFenComp(final String[] fenComp) {
    final StringBuilder builder = new StringBuilder();
    for(String comp : fenComp) {
      builder.append("'").append(comp).append("' ");
    }
    builder.deleteCharAt(builder.length() - 1);
    return builder.toString();
  }

  /*-- BoardState Methods --*/

  private List<Piece> parseBoardState(final String input) {
    int x = 0, y = 0, count = 0;
    final List<Piece> pieces = new ArrayList<>();
    for (char token : input.toCharArray()) {
      final Piece piece = Piece.toPiece(new Position(x, y), token);
      if (piece != null) {
        pieces.add(piece);
        x++;
        count++;
      } else if (token >= '1' && token <= '8') {
        int spaces = '1' - token;
        x += spaces;
        count += spaces;
      } else if (token == '/') {
        x = 0;
        y++;
      } else {
        log.error("Unexpected token: [{}] encountered", token);
        return null;
      }
    }
    if (count == 64) {
      return pieces;
    } else {
      log.error("Input accounted for: [{}] tiles instead of [{}]", count, 64);
      return null;
    }
  }
}
