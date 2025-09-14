package com.christian.apps.chess;

import com.christian.apps.util.Position;

public class Board {

  public static final char EMPTY_TILE = '\u0000';

  private static final int BOARD_WIDTH = 8;
  private static final int BOARD_HEIGHT = 8;

  private final char[][] board;

  public Board() {
    board = new char[BOARD_HEIGHT][BOARD_WIDTH];
  }

  /*-- Methods --*/

  public void placeSymbolAt(final Position position, final char symbol) {
    board[position.getY()][position.getX()] = symbol;
  }

  public void removeSymbolAt(final Position position) {
    board[position.getY()][position.getX()] = EMPTY_TILE;
  }

  public char getTile(final Position position) {
    return board[position.getY()][position.getX()];
  }

  /*-- Helper Methods --*/
}
