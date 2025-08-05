package com.christian.games.piece;

import static com.christian.games.piece.Color.WHITE;
import static com.christian.games.piece.Type.ROOK;

import com.christian.games.util.Position;
import java.util.List;

public class Rook extends Piece {

  public static final String WHITE_SYMBOL = "R";
  public static final String BLACK_SYMBOL = "r";
  public static final List<Position> DIRECTIONS = List.of(
      Position.NORTH, Position.EAST, Position.SOUTH, Position.WEST
  );

  public Rook(Position position, Color color) {
    super(position, ROOK, color);
  }

  @Override
  public String getSymbol() {
    return getColor() == WHITE ? WHITE_SYMBOL : BLACK_SYMBOL;
  }
}
