package com.christian.games.piece;

import static com.christian.games.piece.Color.WHITE;
import static com.christian.games.piece.Type.BISHOP;

import com.christian.games.util.Position;

public class Bishop extends Piece {

  public static final String WHITE_SYMBOL = "B";
  public static final String BLACK_SYMBOL = "b";

  public Bishop(Position position, Color color) {
    super(position, BISHOP, color);
  }

  @Override
  public String getSymbol() {
    return getColor() == WHITE ? WHITE_SYMBOL : BLACK_SYMBOL;
  }
}
