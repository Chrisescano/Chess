package com.christian.games.piece;

import static com.christian.games.piece.Color.WHITE;
import static com.christian.games.piece.Type.KING;

import com.christian.games.util.Position;
import java.util.List;

public class King extends Piece {

  public static final String WHITE_SYMBOL = "K";
  public static final String BLACK_SYMBOL = "k";
  public static final List<Position> DIRECTIONS = List.of(
      Position.NORTH, Position.NORTH_EAST, Position.EAST, Position.SOUTH_EAST,
      Position.SOUTH, Position.SOUTH_WEST, Position.WEST, Position.NORTH_WEST
  );

  public King(Position position, Color color) {
    super(position, KING, color);
  }

  @Override
  public String getSymbol() {
    return getColor() == WHITE ? WHITE_SYMBOL : BLACK_SYMBOL;
  }
}
