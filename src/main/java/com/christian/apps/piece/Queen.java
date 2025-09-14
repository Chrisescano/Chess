package com.christian.apps.piece;

import com.christian.apps.util.Position;

public class Queen extends Piece {

  public static final char WHITE_SYMBOL = 'Q';
  public static final char BLACK_SYMBOL = 'q';

  public Queen(Position position, boolean isWhite) {
    super(position, Type.QUEEN, isWhite);
    setSymbol(isWhite ? WHITE_SYMBOL : BLACK_SYMBOL);
  }
}
