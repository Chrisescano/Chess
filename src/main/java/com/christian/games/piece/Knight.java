package com.christian.games.piece;

import static com.christian.games.piece.Color.WHITE;
import static com.christian.games.piece.Type.KNIGHT;

import com.christian.games.util.Position;

public class Knight extends Piece {

  public static final String WHITE_SYMBOL = "N";
  public static final String BLACK_SYMBOL = "n";

  public Knight(Position position, Color color) {
    super(position, KNIGHT, color);
  }

  @Override
  public String getSymbol() {
    return getColor() == WHITE ? WHITE_SYMBOL : BLACK_SYMBOL;
  }
}
