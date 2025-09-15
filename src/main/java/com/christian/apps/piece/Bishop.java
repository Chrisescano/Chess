package com.christian.apps.piece;

import com.christian.apps.util.Position;
import java.util.List;

public class Bishop extends Piece {

  public static final char WHITE_SYMBOL = 'B';
  public static final char BLACK_SYMBOL = 'b';

  public Bishop(Position position, boolean isWhite) {
    super(position, PieceType.BISHOP, isWhite);
    setSymbol(isWhite ? WHITE_SYMBOL : BLACK_SYMBOL);
  }

  /*-- Methods --*/

  @Override
  public List<Position> getDirections() {
    return List.of(Position.NORTH_EAST, Position.SOUTH_EAST, Position.SOUTH_WEST, Position.NORTH_WEST);
  }
}
