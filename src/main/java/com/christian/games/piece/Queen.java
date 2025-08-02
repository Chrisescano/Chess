package com.christian.games.piece;

import static com.christian.games.piece.Color.WHITE;
import static com.christian.games.piece.Type.QUEEN;

import com.christian.games.util.Position;

public class Queen extends Piece {

  public static final String WHITE_SYMBOL = "Q";
  public static final String BLACK_SYMBOL = "q";

  public Queen(Position position, Color color) {
    super(position, QUEEN, color);
  }

  @Override
  public String getSymbol() {
    return getColor() == WHITE ? WHITE_SYMBOL : BLACK_SYMBOL;
  }
}
