package com.christian.games.piece;

import static com.christian.games.piece.Color.WHITE;
import static com.christian.games.piece.Type.PAWN;

import com.christian.games.util.Position;
import java.util.List;

public class Pawn extends Piece {

  public static final String WHITE_SYMBOL = "P";
  public static final String BLACK_SYMBOL = "p";
  public static final List<Position> NORTH_DIRECTIONS = List.of(
      Position.NORTH, Position.NORTH_WEST, Position.NORTH_EAST
  );
  public static final List<Position> SOUTH_DIRECTIONS = List.of(
      Position.SOUTH, Position.SOUTH_WEST, Position.SOUTH_EAST
  );

  private Color p1Color;

  public Pawn(Position position, Color color) {
    super(position, PAWN, color);
  }

  @Override
  public String calculateSymbol() {
    return getColor() == WHITE ? WHITE_SYMBOL : BLACK_SYMBOL;
  }

  @Override
  public List<Position> getDirections() {
    return getColor() == WHITE ^ p1Color == WHITE ? SOUTH_DIRECTIONS : NORTH_DIRECTIONS;
  }

  /*-- Getters/Setters --*/

  public Color getP1Color() {
    return p1Color;
  }

  public void setP1Color(final Color p1Color) {
    this.p1Color = p1Color;
  }
}
