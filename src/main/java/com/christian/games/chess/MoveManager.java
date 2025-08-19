package com.christian.games.chess;

import com.christian.games.piece.Piece;
import com.christian.games.util.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoveManager {

  private static final Logger log = LoggerFactory.getLogger(MoveManager.class);

  /*-- Methods --*/

  public static void generateMoveMap(final Piece piece) {
    log.debug("Generating move map for piece [{}]", piece);
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

    log.debug("Generated move map {}", moveMap);
    piece.setMoveMap(moveMap);
  }

  public static void shiftMoveMap(final Piece piece, final Position destination) {
    log.debug("Shifting piece [{}] move map to destination [{}]", piece, destination);
    int xDiff = destination.getX() - piece.getFile();
    int yDiff = destination.getY() - piece.getRank();
    Position diff = new Position(xDiff, yDiff);

    Map<Integer, List<Position>> moveMap = piece.getMoveMap();
    for (Integer directionId : moveMap.keySet()) {
      moveMap.get(directionId).forEach(move -> move.add(diff));
    }
  }

  public static void markMoveMap(final Piece piece, final char[][] board) {
    log.debug("Marking move map for piece [{}]", piece);
    Map<Integer, List<Position>> moveMap = piece.getMoveMap();
    for (Integer directionId : moveMap.keySet()) {
      markMoveMap(moveMap.get(directionId), piece.getCharSymbol(), board);
    }
  }

  public static void markMoveMap(final List<Position> moves, final char pieceSymbol, final char[][] board) {
    if (!moves.isEmpty()) {
      log.debug("Marking move map section {} for piece with symbol [{}]", moves, pieceSymbol);
    }
    boolean isMoveListInvalid = false;
    for (Position move : moves) {
      if (isMoveListInvalid) {
        move.disable();
        continue;
      }

      if (isOutOfBounds(move)) {
        move.disable();
        isMoveListInvalid = true;
        log.debug("Disabling move [{}] because it's out of bounds", move);
        continue;
      }

      char moveToken = board[move.getY()][move.getX()];
      if (moveToken != Character.MIN_VALUE) {
        log.debug("Piece symbol [{}] encountered another symbol [{}] for move [{}]",pieceSymbol, moveToken, move);
        if (areEnemies(pieceSymbol, moveToken)) {
          move.enable();
        } else {
          move.disable();
        }
        isMoveListInvalid = true;
        continue;
      }

      move.enable();
    }
  }

  public static boolean pieceMoveMapContains(final Piece piece, final Position position) {
    log.debug("Checking if piece [{}] move map contains [{}]", piece, position);
    for (Map.Entry<Integer, List<Position>> entry : piece.getMoveMap().entrySet()) {
      if (pieceMoveMapContains(entry.getValue(), position)) {
        return true;
      }
    }
    return false;
  }

  public static boolean pieceMoveMapContains(final List<Position> moves, final Position position) {
    log.debug("Checking if moves {} contain position [{}]", moves, position);
    for (Position move : moves) {
      if (move.equals(position) && move.isEnabled()) {
        return true;
      }
    }
    return false;
  }

  public static List<Position> getMoveMapSection(final Piece piece, final Position position) {
    log.debug("Checking if piece [{}] move map section contains [{}]", piece, position);
    for (Map.Entry<Integer, List<Position>> entry : piece.getMoveMap().entrySet()) {
      if (pieceMoveMapContains(entry.getValue(), position)) {
        return entry.getValue();
      }
    }
    return Collections.emptyList();
  }

  public static boolean isOutOfBounds(final Position position) {
    return position.getX() < 0 || position.getX() > 7 ||
        position.getY() < 0 || position.getY() > 7;
  }

  public static boolean areEnemies(final char symbol1, final char symbol2) {
    return Character.isUpperCase(symbol1) ^ Character.isUpperCase(symbol2);
  }
}
