package com.christian.games.piece;

import static com.christian.games.piece.Color.WHITE;
import static com.christian.games.piece.Type.QUEEN;

import com.christian.games.util.Position;
import java.util.List;

public class Queen extends Piece {

  public static final String WHITE_SYMBOL = "Q";
  public static final String BLACK_SYMBOL = "q";
  public static final List<Position> DIRECTIONS = List.of(
      Position.NORTH, Position.NORTH_EAST, Position.EAST, Position.SOUTH_EAST,
      Position.SOUTH, Position.SOUTH_WEST, Position.WEST, Position.NORTH_WEST
  );
  public Queen(Position position, Color color) {
    super(position, QUEEN, color);
  }

  @Override
  public String calculateSymbol() {
    return getColor() == WHITE ? WHITE_SYMBOL : BLACK_SYMBOL;
  }

  @Override
  public List<Position> getDirections() {
    return DIRECTIONS;
  }
}
