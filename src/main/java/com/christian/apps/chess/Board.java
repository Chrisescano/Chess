package com.christian.apps.chess;

import com.christian.apps.util.Position;
import java.util.List;

public class Board {

  public static final char EMPTY_TILE = '\u0000';

  private static final int BOARD_WIDTH = 8;
  private static final int BOARD_HEIGHT = 8;

  private final char[][] board;

  public Board() {
    board = new char[BOARD_HEIGHT][BOARD_WIDTH];
  }

  /*-- Methods --*/

  public static boolean isOutOfBounds(final Position position) {
    return position.getX() < 0 || position.getX() > 7 ||
        position.getY() < 0 && position.getY() > 7;
  }

  public void placeSymbolAt(final Position position, final char symbol) {
    board[position.getY()][position.getX()] = symbol;
  }

  public void removeSymbolAt(final Position position) {
    board[position.getY()][position.getX()] = EMPTY_TILE;
  }

  public char getTile(final Position position) {
    return board[position.getY()][position.getX()];
  }

  public char[] getRangeOfTiles(final List<Position> path) {
    final char[] tileRange = new char[path.size()];
    for (int i = 0; i < tileRange.length; i++) {
      tileRange[i] = getTile(path.get(i));
    }
    return tileRange;
  }

  /*-- Helper Methods --*/
}
