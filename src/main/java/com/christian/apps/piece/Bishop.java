package com.christian.apps.piece;

import com.christian.apps.util.Position;

public class Bishop extends Piece {

  public static final char WHITE_SYMBOL = 'B';
  public static final char BLACK_SYMBOL = 'b';

  public Bishop(Position position, boolean isWhite) {
    super(position, Type.BISHOP, isWhite);
    setSymbol(isWhite ? WHITE_SYMBOL : BLACK_SYMBOL);
  }
}
