package com.christian.games.parser.notation;

import static com.christian.games.piece.Type.BISHOP;
import static com.christian.games.piece.Type.KING;
import static com.christian.games.piece.Type.KNIGHT;
import static com.christian.games.piece.Type.PAWN;
import static com.christian.games.piece.Type.QUEEN;
import static com.christian.games.piece.Type.ROOK;

import com.christian.games.parser.Parser;
import com.christian.games.piece.Bishop;
import com.christian.games.piece.King;
import com.christian.games.piece.Knight;
import com.christian.games.piece.Queen;
import com.christian.games.piece.Rook;
import com.christian.games.piece.Type;
import com.christian.games.pojo.AlgebraicNotation;
import com.christian.games.util.Position;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlgebraicNotationParser implements Parser<String, AlgebraicNotation> {

  private final Pattern pattern = Pattern.compile("([RNBQK])?([a-f])?([1-8])?(x)?([a-f])([1-8])([+#])?");

  @Override
  public AlgebraicNotation parse(String input) {
    if (input == null) {
      return null;
    }

    Matcher matcher = pattern.matcher(input);
    if (!matcher.matches()) {
      return null;
    }

    return new AlgebraicNotation(
        mapType(matcher.group(1)),
        mapPosition(matcher.group(2), matcher.group(3)),
        mapPosition(matcher.group(4), matcher.group(5)),
        mapOption(matcher.group(6), "x"),
        mapOption(matcher.group(7), "+"),
        mapOption(matcher.group(7), "#")
    );
  }

  @Override
  public String unparse(AlgebraicNotation input) {
    return "";
  }

  private Type mapType(final String typeStr) {
    if (typeStr == null) {
      return PAWN;
    }

    return switch (typeStr) {
      case Rook.WHITE_SYMBOL -> ROOK;
      case Knight.WHITE_SYMBOL -> KNIGHT;
      case Bishop.WHITE_SYMBOL -> BISHOP;
      case Queen.WHITE_SYMBOL -> QUEEN;
      case King.WHITE_SYMBOL -> KING;
      default -> null;
    };
  }

  private Position mapPosition(final String fileStr, final String rankStr) {
    int file = -1, rank = -1;

    if (fileStr != null) {
      file = fileStr.charAt(0) - 'a';
    }

    if (rankStr != null) {
      rank = '8' - rankStr.charAt(0);
    }

    return new Position(file, rank);
  }

  private boolean mapOption(final String optionStr, final String trueCondition) {
    return optionStr != null && optionStr.equals(trueCondition);
  }
}
