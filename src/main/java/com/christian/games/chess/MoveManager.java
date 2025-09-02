package com.christian.games.chess;

import com.christian.games.piece.Piece;
import com.christian.games.util.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoveManager {

  private static final Logger log = LoggerFactory.getLogger(MoveManager.class);

  /*-- Methods --*/

  public static void setUpMoveMap(final Piece piece) {
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

  public static void translateMoveMap(final Piece piece, final Position destination) {
    int xDiff = destination.getX() - piece.getFile();
    int yDiff = destination.getY() - piece.getRank();
    Position diff = new Position(xDiff, yDiff);

    Map<Integer, List<Position>> moveMap = piece.getMoveMap();
    for (Integer directionId : moveMap.keySet()) {
      moveMap.get(directionId).forEach(move -> move.translate(diff));
    }
  }

  public static void updateMoveMap(final Piece piece, final Board board) {
    Map<Integer, List<Position>> moveMap = piece.getMoveMap();
    for (Integer directionId : moveMap.keySet()) {
      List<Position> moves = moveMap.get(directionId);
      updateListOfMoves(piece.getCharSymbol(), piece.getPosition(), moves, board.getSymbolsOf(moves));
    }
  }

  public static void updateListOfMoves(final char pieceSymbol, final Position piecePos, final List<Position> moves, final List<Character> boardSymbols) {
    boolean isMovesEligible = true;
    for (int i = 0; i < moves.size(); i++) {
      Position move = moves.get(i);
      Character boardSymbol = boardSymbols.get(i);

      if (!isMovesEligible) {
        move.disable();
        continue;
      }

      boolean eligible = isMoveEligible(piecePos, move, pieceSymbol, boardSymbol);
      if (!eligible) {
        isMovesEligible = false;
      }
      move.setEnabled(eligible);
    }
  }

  /*-- Helper Methods --*/

  private static boolean areEnemies(final char symbol1, final char symbol2) {
    return Character.isUpperCase(symbol1) ^ Character.isUpperCase(symbol2);
  }

  private static boolean isOutOfBounds(final Position position) {
    return position.getX() < 0 || position.getX() > 7 ||
        position.getY() < 0 || position.getY() > 7;
  }

  private static boolean isMoveEligible(final Position oldPos, final Position newPos, final char pieceSymbol, final char boardSymbol) {
    if (isOutOfBounds(newPos)) {
      return false;
    }

    return switch (pieceSymbol) {
      case 'P', 'p' -> isPawnMoveEligible(oldPos.getX(), newPos.getX(), pieceSymbol, boardSymbol);
      default -> isGenericMoveEligible(pieceSymbol, boardSymbol);
    };
  }
  
  private static boolean isGenericMoveEligible(final char pieceSymbol, final char boardSymbol) {
    if (boardSymbol != Board.EMPTY_SYMBOL) {
      return areEnemies(pieceSymbol, boardSymbol);
    }
    return true;
  }

  private static boolean isPawnMoveEligible(final int oldPosFile, final int newPosFile, final char pieceSymbol, final char boardSymbol) {
    if (boardSymbol != Board.EMPTY_SYMBOL) {
      if (areEnemies(pieceSymbol, boardSymbol)) {
        return oldPosFile != newPosFile;
      }
      return false;
    }
    return true;
  }
}
