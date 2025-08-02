package com.christian.games.piece;

import static com.christian.games.piece.Color.WHITE;
import static com.christian.games.piece.Type.KING;

import com.christian.games.util.Position;

public class King extends Piece {

  public static final String WHITE_SYMBOL = "K";
  public static final String BLACK_SYMBOL = "k";

  public King(Position position, Color color) {
    super(position, KING, color);
  }

  @Override
  public String getSymbol() {
    return getColor() == WHITE ? WHITE_SYMBOL : BLACK_SYMBOL;
  }
}
