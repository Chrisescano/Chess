package com.christian.apps.piece;

import com.christian.apps.util.Position;
import java.util.List;

public class King extends Piece {

  public static final char WHITE_SYMBOL = 'K';
  public static final char BLACK_SYMBOL = 'k';

  public King(Position position, boolean isWhite) {
    super(position, Type.KING, isWhite);
    setSymbol(isWhite ? WHITE_SYMBOL : BLACK_SYMBOL);
  }

  /*-- Methods --*/

  @Override
  public List<Position> getDirections() {
    return List.of(
        Position.NORTH, Position.NORTH_EAST, Position.EAST,
        Position.SOUTH_EAST, Position.SOUTH, Position.SOUTH_WEST, Position.WEST, Position.NORTH_WEST
    );
  }
}
