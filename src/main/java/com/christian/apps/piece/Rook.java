package com.christian.apps.piece;

import com.christian.apps.util.Position;
import java.util.List;

public class Rook extends Piece {

  public static final char WHITE_SYMBOL = 'R';
  public static final char BLACK_SYMBOL = 'r';
  public static final Position TOP_LEFT = new Position(0, 0);
  public static final Position TOP_RIGHT = new Position(7, 0);
  public static final Position BOTTOM_LEFT = new Position(0, 7);
  public static final Position BOTTOM_RIGHT = new Position(7, 7);

  public Rook(final Position position, final boolean isWhite) {
    super(position, PieceType.ROOK, isWhite);
    setSymbol(isWhite ? WHITE_SYMBOL : BLACK_SYMBOL);
  }

  /*-- Methods --*/

  @Override
  public List<Position> getDirections() {
    return List.of(Position.NORTH, Position.EAST, Position.SOUTH, Position.WEST);
  }
}
