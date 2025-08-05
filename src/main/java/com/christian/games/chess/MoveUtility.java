package com.christian.games.chess;

import static com.christian.games.piece.Color.WHITE;

import com.christian.games.piece.Bishop;
import com.christian.games.piece.Color;
import com.christian.games.piece.King;
import com.christian.games.piece.Knight;
import com.christian.games.piece.Pawn;
import com.christian.games.piece.Piece;
import com.christian.games.piece.Queen;
import com.christian.games.piece.Rook;
import com.christian.games.util.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoveUtility {

  /*-- Methods --*/

  public static void generateMoveMap(final Piece piece) {
    int range = switch (piece.getType()) {
      case PAWN, KNIGHT, KING -> 1;
      case ROOK, BISHOP, QUEEN -> 7;
    };

    Map<Integer, List<Position>> moveMap = new HashMap<>();
    for (Position direction : directionsOf(piece)) {
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
      for (Position move : moveMap.get(directionId)) {
        move.add(diff);
      }
    }
  }

  public static void markMoveMap(final Piece piece, char[][] board) {
    Map<Integer, List<Position>> moveMap = piece.getMoveMap();
    for (Integer directionId : moveMap.keySet()) {
      boolean isListOfMovesValid = true;
      for (Position move : moveMap.get(directionId)) {
        if (!isListOfMovesValid) {
          move.setEnabled(false);
          continue;
        }

        if (isOutOfBounds(move)) {
          move.setEnabled(false);
          continue;
        }

        char moveSymbol = board[move.getY()][move.getX()];
        if (moveSymbol == '\0') {
          move.setEnabled(true);
          continue;
        }

        if (piece.isEnemyOf(moveSymbol)) {
          move.setEnabled(true);
        } else {
          move.setEnabled(false);
          isListOfMovesValid = false;
        }
      }
    }
  }

  public static boolean isOutOfBounds(final Position position) {
    return position.getX() < 0 || position.getX() > 7 ||
        position.getY() < 0 || position.getY() > 7;
  }

  /*-- Helper Methods --*/

  private static List<Position> directionsOf(final Piece piece) {
    return switch (piece.getType()) {
      case PAWN -> {
        Color p1Color = ((Pawn) piece).getP1Color();
        yield piece.getColor() == WHITE ^ p1Color == WHITE ? Pawn.SOUTH_DIRECTIONS : Pawn.NORTH_DIRECTIONS;
      }
      case ROOK -> Rook.DIRECTIONS;
      case KNIGHT -> Knight.DIRECTIONS;
      case BISHOP -> Bishop.DIRECTIONS;
      case QUEEN -> Queen.DIRECTIONS;
      case KING -> King.DIRECTIONS;
    };
  }
}
