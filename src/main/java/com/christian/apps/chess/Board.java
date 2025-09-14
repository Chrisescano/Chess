package com.christian.apps.chess;

import com.christian.apps.piece.Piece;
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

  public boolean placePiece(final Piece piece) {
    final char tile = getTile(piece.getPosition());
    if (tile == EMPTY_TILE) {
      placeAt(piece.getPosition(), piece.getSymbol());
      return true;
    }
    return false;
  }

  public boolean removePiece(final Piece piece) {
    final char tile = getTile(piece.getPosition());
    if (piece.getSymbol() == tile) {
      removeAt(piece.getPosition());
      return true;
    }
    return false;
  }

  public char getTile(final Position position) {
    return board[position.getY()][position.getX()];
  }

  /*-- Helper Methods --*/

  private void placeAt(final Position position, final char symbol) {
    board[position.getY()][position.getX()] = symbol;
  }

  private void removeAt(final Position position) {
    board[position.getY()][position.getX()] = EMPTY_TILE;
  }
}
