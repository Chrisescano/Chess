package com.christian.apps.piece;

import com.christian.apps.util.Position;

public class Rook extends Piece {

  public static final char WHITE_SYMBOL = 'R';
  public static final char BLACK_SYMBOL = 'r';

  public Rook(final Position position, final boolean isWhite) {
    super(position, Type.ROOK, isWhite);
    setSymbol(isWhite ? WHITE_SYMBOL : BLACK_SYMBOL);
  }
}
