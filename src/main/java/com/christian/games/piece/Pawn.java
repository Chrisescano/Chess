package com.christian.games.piece;

import static com.christian.games.piece.Color.WHITE;
import static com.christian.games.piece.Type.PAWN;

import com.christian.games.util.Position;

public class Pawn extends Piece {

  public static final String WHITE_SYMBOL = "P";
  public static final String BLACK_SYMBOL = "p";

  public Pawn(Position position, Color color) {
    super(position, PAWN, color);
  }

  @Override
  public String getSymbol() {
    return getColor() == WHITE ? WHITE_SYMBOL : BLACK_SYMBOL;
  }
}
