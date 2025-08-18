package com.christian.games.chess;

import com.christian.games.piece.Piece;
import com.christian.games.util.Position;
import java.util.ArrayList;
import java.util.List;

public class ChessUtility {

  /*-- Methods --*/

  public static void generateMoveMap(final Piece piece) {
    int range = switch (piece.getType()) {
      case PAWN, KNIGHT, KING -> 1;
      case ROOK, BISHOP, QUEEN -> 7;
    };

    List<Position> moves = new ArrayList<>();
    for (Position direction : piece.getDirections()) {
      for (int i = 0; i < range; i++) {
        int moveX = piece.getFile() + (direction.getX() * (i + 1));
        int moveY = piece.getRank() + (direction.getY() * (i + 1));
        moves.add(new Position(moveX, moveY));
      }
    }

    piece.setMoves(moves);
  }

  public static void updateMoveMap(final Piece piece, final Position destination) {
    int xDiff = destination.getX() - piece.getFile();
    int yDiff = destination.getY() - piece.getRank();
    Position diff = new Position(xDiff, yDiff);

    List<Position> moves = piece.getMoves();
    for (Position move : moves) {
      move.add(diff);
    }
  }

  public static void markMoveMap(final Piece piece, char[][] board) {
    boolean validDirection = true;
    for (Position move : piece.getMoves()) {
      if (piece.isOneTileAwayFrom(move)) {
        validDirection = true;
      }

      if (!validDirection) {
        move.disable();
        continue;
      }

      if (isOutOfBounds(move)) {
        move.disable();
        validDirection = false;
        continue;
      }

      char tileSymbol = board[move.getY()][move.getX()];
      if (tileSymbol != Character.MIN_VALUE) {
        if (piece.isEnemyOf(tileSymbol)) {
          move.enable();
        } else {
          move.disable();
        }
        validDirection = false;
        continue;
      }

      move.enable();
    }
  }

  public static boolean isOutOfBounds(final Position position) {
    return position.getX() < 0 || position.getX() > 7 ||
        position.getY() < 0 || position.getY() > 7;
  }
}
