package com.christian.apps.piece;

import com.christian.apps.util.Position;
import java.util.List;

public class Pawn extends Piece {

  public static final char WHITE_SYMBOL = 'P';
  public static final char BLACK_SYMBOL = 'p';

  public Pawn(Position position, boolean isWhite) {
    super(position, Type.PAWN, isWhite);
    setSymbol(isWhite ? WHITE_SYMBOL : BLACK_SYMBOL);
  }

  /*-- Methods --*/

  @Override
  public List<Position> getDirections() {
    return isWhite() ? List.of(Position.NORTH, Position.NORTH_EAST, Position.NORTH_WEST) :
        List.of(Position.SOUTH, Position.SOUTH_EAST, Position.SOUTH_WEST);
  }
}
