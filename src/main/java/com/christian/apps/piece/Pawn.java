package com.christian.apps.piece;

import com.christian.apps.util.Position;

public class Pawn extends Piece {

  public static final char WHITE_SYMBOL = 'P';
  public static final char BLACK_SYMBOL = 'p';

  public Pawn(Position position, boolean isWhite) {
    super(position, Type.PAWN, isWhite);
    setSymbol(isWhite ? WHITE_SYMBOL : BLACK_SYMBOL);
  }
}
