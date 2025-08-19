package com.christian.games.chess;

import com.christian.games.piece.Piece;
import com.christian.games.util.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessUtility {

  /*-- Methods --*/

  public static void generateMoveMap(final Piece piece) {
    int range = switch (piece.getType()) {
      case PAWN, KNIGHT, KING -> 1;
      case ROOK, BISHOP, QUEEN -> 7;
    };

    Map<Integer, List<Position>> moveMap = new HashMap<>();
    for (Position direction : piece.getDirections()) {
      List<Position> moves = new ArrayList<>();
      for (int i = 0; i < range; i++) {
        int moveX = piece.getFile() + (direction.getX() * (i + 1));
        int moveY = piece.getRank() + (direction.getY() * (i + 1));
        moves.add(new Position(moveX, moveY));
      }
      moveMap.put(direction.toId(), moves);
    }

    piece.setMoveMap(moveMap);
  }

  public static void updateMoveMap(final Piece piece, final Position destination) {
    int xDiff = destination.getX() - piece.getFile();
    int yDiff = destination.getY() - piece.getRank();
    Position diff = new Position(xDiff, yDiff);

    Map<Integer, List<Position>> moveMap = piece.getMoveMap();
    for (Integer directionId : moveMap.keySet()) {
      moveMap.get(directionId).forEach(move -> move.add(diff));
    }
  }

  public static void markMoveMap(final Piece piece, char[][] board) {
    /*
    need to rework this method
    TODO: for a move to be enabled do the following:
      (1) check if move is out of bounds
        - yes ? disable and break
      (2) check if move has piece occupied on it
        - yes ? check if piece is enemy
          - yes ? enable and break
          - no ? disable and break
      (3) if all checks fail then enable move

    TODO: still need to handle special moves
      option 1:
        - define list of special moves in Piece (init empty list)
        - when checking if movemap contains pos check special moves first then movemap
        - when marking movemap check if special moves are valid
          - get move
          - check if can be enabled, otherwise disable
          - if none then check if special move generated
     */
  }

  public static boolean isOutOfBounds(final Position position) {
    return position.getX() < 0 || position.getX() > 7 ||
        position.getY() < 0 || position.getY() > 7;
  }
}
