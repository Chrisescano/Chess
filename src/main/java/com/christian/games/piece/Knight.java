package com.christian.games.piece;

import static com.christian.games.piece.Color.WHITE;
import static com.christian.games.piece.Type.KNIGHT;

import com.christian.games.util.Position;
import java.util.List;

public class Knight extends Piece {

  public static final String WHITE_SYMBOL = "N";
  public static final String BLACK_SYMBOL = "n";
  public static final List<Position> DIRECTIONS = List.of(
      Position.combine(Position.NORTH, Position.NORTH_WEST),
      Position.combine(Position.NORTH, Position.NORTH_EAST),
      Position.combine(Position.EAST, Position.NORTH_EAST),
      Position.combine(Position.EAST, Position.SOUTH_EAST),
      Position.combine(Position.SOUTH, Position.SOUTH_EAST),
      Position.combine(Position.SOUTH, Position.SOUTH_WEST),
      Position.combine(Position.WEST, Position.SOUTH_WEST),
      Position.combine(Position.WEST, Position.NORTH_WEST)
  );

  public Knight(Position position, Color color) {
    super(position, KNIGHT, color);
  }

  @Override
  public String calculateSymbol() {
    return getColor() == WHITE ? WHITE_SYMBOL : BLACK_SYMBOL;
  }
}
