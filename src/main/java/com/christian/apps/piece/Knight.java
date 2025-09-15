package com.christian.apps.piece;

import com.christian.apps.util.Position;
import java.util.List;

public class Knight extends Piece {

  public static final char WHITE_SYMBOL = 'N';
  public static final char BLACK_SYMBOL = 'n';

  public Knight(Position position, boolean isWhite) {
    super(position, PieceType.KNIGHT, isWhite);
    setSymbol(isWhite ? WHITE_SYMBOL : BLACK_SYMBOL);
  }

  /*-- Methods --*/

  @Override
  public List<Position> getDirections() {
    return List.of(
        new Position(-1, -2), //north left
        new Position(1, -2),  //north right
        new Position(2, -1),  //east up
        new Position(2, 1),   //east down
        new Position(-1, 2),  //south left
        new Position(1, 2),   //south right
        new Position(-2, -1), //west up
        new Position(-2, 1)   //west down
    );
  }
}
