package com.christian.apps.piece;

import com.christian.apps.util.Position;

public class King extends Piece {

  public static final char WHITE_SYMBOL = 'K';
  public static final char BLACK_SYMBOL = 'k';

  public King(Position position, boolean isWhite) {
    super(position, Type.KING, isWhite);
    setSymbol(isWhite ? WHITE_SYMBOL : BLACK_SYMBOL);
  }
}
