package com.christian.apps.piece;

import com.christian.apps.util.Position;

public class Knight extends Piece {

  public static final char WHITE_SYMBOL = 'N';
  public static final char BLACK_SYMBOL = 'n';

  public Knight(Position position, boolean isWhite) {
    super(position, Type.KNIGHT, isWhite);
    setSymbol(isWhite ? WHITE_SYMBOL : BLACK_SYMBOL);
  }
}
